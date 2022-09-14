package com.company.src.dynamicProgramming.knapsack01;

/**
 * 01背包问题，容量为C的背包，物品的价值为[v1,...vn], 重量为[w1,..., wn]，问最多能装价值多少的物品
 */
public class Knapsack01 {
    /**
     * 解法1：递归，自顶向下实现。状态方程为：
     * F(n) 为[0,n]范围内背包所能装价值最多的
     *
     * @param w
     * @param v
     * @param C
     * @return
     */
    int knapsack01(int[] w, int[] v, int C){
        return bestValue(w,v,v.length-1, C);
    }

    //从[0,index]处最大价值
    int bestValue(int[] w, int[] v, int index, int c){
        if (index<0 || c<=0)
            return 0;
        // 不把index塞进背包
        int res = bestValue(w,v,index-1, c);

        // 把index塞进背包。 两种情况取最大值
        if (w[index] < c){
            res = Math.max(res, v[index]+bestValue(w,v,index-1, c-w[index]));
        }
        return res;
    }

    /**
     * 解法2，记忆化搜索 解决重叠子问题
     */
    int knapsack01_2(int[] w, int[] v, int C){
        memo = new int[v.length][C+1];// 初始化为-1
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j <C+1 ; j++) {
                memo[i][j]=-1;
            }
        }
        return bestValue(w,v,v.length-1, C);
    }
    int[][] memo; //
    int bestValue2(int[] w, int[] v, int index, int c){
        if (index<0 || c<=0)
            return 0;

        if (memo[index][c]!=-1)
            return memo[index][c];

        // 不把index塞进背包
        int res = bestValue2(w,v,index-1, c);

        // 把index塞进背包。 两种情况取最大值
        if (w[index] <= c){
            res = Math.max(res, v[index]+bestValue2(w,v,index-1, c-w[index]));
        }
        memo[index][c] = res;
        return res;
    }

    /**
     * 解法3：动态规划. 转移方程为：F(i, c) = Max( F(i-1, c), v[i]+F(i-1, c-w[i]) )
     *
     * 时间复杂度 O(n*C)
     * 空间复杂度 O(n*C)
     * @param w
     * @param v
     * @param C
     * @return
     */
    int knapsack01_3(int[] w, int[] v, int C){
        int len = v.length;
        int[][] dp = new int[len][C+1];

        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= C ; j++) {
                // base case: 第0号物品
                if (i==0 && w[i]<=j){
                    dp[i][j] = v[i];
                    continue;
                }

                // i不放进
                dp[i][j] = dp[i-1][j];
                if (j>=w[i]){ // i放进
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i-1][j-w[i]]);
                }
            }
        }

        return dp[len-1][C];
    }

    // 01背包，动态规划空间复杂度优化。由状态转移方程可知，只需要保持第i行和第i-1行就行，没必要维持n行。
    // 空间复杂度可降低为 O(2*C) = O(C)
    //F(i, c) = Max( F(i-1, c), v[i]+F(i-1, c-w[i]) )
    int knapsack01_4(int[] w, int[] v, int C){
        int len = v.length;
        int[][] dp = new int[2][C+1];

        // 第0号物品
        for (int i = 0; i <=C ; i++) {
            dp[0][i] =  w[0]<=i ? v[i] :0 ;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= C ; j++) {
                // i不放进
                dp[i%2][j] = dp[(i-1)%2][j];
                if (j>=w[i]){ // i放进
                    dp[i%2][j] = Math.max(dp[i%2][j], v[i] + dp[(i-1)%2][j-w[i]]);
                }
            }
        }

        return dp[(len-1)%2][C];
    }

    // 01背包问题，再次优化，只使用一行数组来实现。因为dp每次更新只考虑上一行的i和i前面的值，所以可以从右边开始更新
    int knapsack01_5(int[] w, int[] v, int C){
        int len = v.length;
        int[] dp = new int[C+1];

        // 第0号物品
        for (int i = 0; i <=C ; i++) {
            dp[i] =  w[0]<=i ? v[i] :0 ;
        }

        for (int i = 1; i < len; i++) {
            for (int j = C; j >= w[i] ; j--) {  // 需要逆序
                // i不放进
                //dp[j] = dp[j];
                if (j>=w[i]){ // i放进
                    dp[j] = Math.max(dp[j], v[i] + dp[j-w[i]]);
                }

            }
        }

        return dp[C];
    }

}
