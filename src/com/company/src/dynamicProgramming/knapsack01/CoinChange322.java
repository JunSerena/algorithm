package com.company.src.dynamicProgramming.knapsack01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 104
 *
 * 链接：https://leetcode.cn/problems/coin-change/
 */
public class CoinChange322 {
    public static void main(String[] args) {
        CoinChange322 ins = new CoinChange322();
        int[] coins = {186,419,83,408};
        //test();
        System.out.println(ins.coinChange2(coins, 6249));
    }

    // 解一：递归。会超时，可能是有问题的
    public int coinChange(int[] coins, int amount) {

        int[] copy = findPossibleSub(coins, amount);
        int[] count = new int[2];
        count[1] = Integer.MAX_VALUE;
        findChange(copy, amount, count);
        if (count[1]!=Integer.MAX_VALUE)
            return count[1];
        return -1;

    }
    // count[0]存储当前轮次的count, count[1]存最小count
    public static void findChange(int[] coins, int target, int[] count){
        if (target<0)
            return;
        if (target==0){
            count[1] = Math.min(count[0], count[1]);
            return;
        }

        int len = coins.length-1;
        for (int i = 0; i <=len ; i++) {
            if (target>=coins[i]){
                count[0] = count[0]+1;
                findChange(coins, target-coins[i], count);
                count[0] = count[0]-1;
            }
        }

    }

    /**
     * 官方！解法1.2，递归改记忆化搜索。
     * 最优解可以从其子问题的最优解构造出来。如何将问题分解成子问题？
     * 假设我们知道 F(S)，即组成金额 S 最少的硬币数，最后一枚硬币的面值是 C。那么由于问题的最优子结构，转移方程应为：
     *
     * F(S) = F(S - C) + 1
     * 不知道最后一枚硬币的面值，所以需要便利去找到count最小的
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1_2(int[] coins, int amount) {

        memo = new int[amount+1];
        return findChange2(coins, amount);

    }
    // 递归改记忆化搜索
    int[] memo;
    public int findChange2(int[] coins, int target){
        if (target<0)
            return -1;
        if (target==0){
            return 0;
        }
        if (memo[target]!=0)
            return memo[target];

        int len = coins.length-1;
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i <=len ; i++) {
            int res = findChange2(coins, target-coins[i]);
            if (res!=-1){
                minCount = Math.min(minCount, res+1);
            }

        }
        memo[target] = minCount == Integer.MAX_VALUE ? -1 : minCount;
        return memo[target];

    }


    /**
     * 解法三：根据记忆化搜索改动态规划。
     *      * F(S) = F(S - C) + 1
     *      * 不知道最后一枚硬币的面值，所以需要便利去找到count最小的
     * 时间复杂度O(Sn)，其中 S 是金额，n 是面额种数。我们一共需要计算 O(S) 个状态，S 为题目所给的总金额。
     * 对于每个状态，每次需要枚举 n 个面额来转移状态，所以一共需要 O(Sn) 的时间复杂度。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange4(int[] coins, int amount) {

        int[] dp = new int[amount+1];

        Arrays.fill(dp, -1);
        dp[0] =0;
        for (int i = 1; i <=amount ; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != -1) {
                    min = Math.min(min, dp[i - coin] + 1);
                }
            }
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
        }

        return dp[amount];
    }



    public static int[] findPossibleSub(int[] coins, int amount){
        ArrayList<Integer> list = new ArrayList<>();

        for (int a: coins  ) {
            if (a<=amount){
                list.add(a);
            }
        }

        list.sort(Comparator.naturalOrder());
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }


    /**
     * 解法二：动态规划，背包问题
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        if (amount==0)
            return 0;

        int[] copy = findPossibleSub(coins, amount); // 顺序排序
        int len = copy.length;
        // 所有值都比amount大，不可能兑换
        if (len==0)
            return -1;
        int[][] dp = new int[len][amount+1];

        //dp[0][i] 只使用第一种硬币
        for (int j = 1; j <= amount; j++) {
            if (j>=copy[0] && j%copy[0]==0){
                dp[0][j]=j/copy[0];
            }else
                dp[0][j] =-1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= amount; j++) {
               int limit = j/copy[i];
               int minCount = Integer.MAX_VALUE;
                for (int k = limit; k >=0 ; k--) {
                    int remainder = j - k*copy[i];
                    int prevCount = dp[i-1][remainder];
                    if (prevCount !=-1 ){
                        minCount = Math.min(minCount, prevCount+k);
                    }
                }
                dp[i][j] = minCount == Integer.MAX_VALUE ? -1 : minCount;

            }
        }
        return dp[len-1][amount];
    }


    /**
     * 解法二：动态规划 空间优化
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        if (amount==0)
            return 0;

        int[] copy = findPossibleSub(coins, amount); // 找到比amount小的，顺序排序
        int len = copy.length;
        // 所有值都比amount大，不可能兑换
        if (len==0)
            return -1;
        int[] dp = new int[amount+1];

        //dp[0][i] 只使用第一种硬币
        for (int j = 1; j <= amount; j++) {
            if (j>=copy[0] && j%copy[0]==0){
                dp[j]=j/copy[0];
            }else
                dp[j] =-1;
        }

        for (int i = 1; i < len; i++) {
            for (int j =amount; j >=copy[i]; j--) {
                int limit = j/copy[i];
                int minCount = Integer.MAX_VALUE;

                for (int k = limit; k >=0 ; k--) { // copy[i]到底使用了几次
                    int remainder = j - k*copy[i];
                    int prevCount = dp[remainder];
                    if (prevCount !=-1 ){
                        minCount = Math.min(minCount, prevCount+k);
                    }
                }
                dp[j] = minCount == Integer.MAX_VALUE ? -1 : minCount;

            }
        }
        return dp[amount];
    }

//    public static void test() {
//        int[] coins = {186,419,83,408};
//        for (int i = 0; i<=33 ; i++) {
//            for (int j = 0;  j<=14; j++) {
//                for (int k = 0; k<=75 ; k++) {
//                    for (int l = 0; l<=15; l++) {
//                        int sum = coins[0]*i +coins[1]*j +coins[2]*k +coins[3]*l;
//                        if ( sum== 6249){
//                            int count = i+j+k+l;
//                            System.out.println("i=" + i+ ",j="+j+",k="+k+",l="+l + "  count="+count );
//                        }
//
//
//                        //System.out.println(sum);
//                    }
//                }
//            }
//        }
//    }
}
