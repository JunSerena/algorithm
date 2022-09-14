package com.company.src.dynamicProgramming;

/**
 * 343. 整数拆分
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 *
 * 返回 你可以获得的最大乘积 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: n = 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 *
 * 提示:
 *
 * 2 <= n <= 58
 *
 * 链接：https://leetcode.cn/problems/integer-break/
 */
public class IntegerBreak343 {
    /**
     * 动态规划：求最优子结构。
     * 比如 4可以拆分成 1+3，2+2。只要知道1、2、3的最优解，就能轻易求的4的最优解。
     * @param n
     * @return
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;

        for (int i = 2; i <=n ; i++) { // 从小到大补充dp[n]
            int max = 0;
            for (int j = 1; j <=i/2 ; j++) { //枚举所有可能拆分成两个数的情况，再比较哪个是最优解
                int a = dp[j], b= dp[i-j];
                //拆分的两数，可以不再拆分，也可以再拆分成子项，就看哪个值更大。
                if (j>dp[j])
                    a=j;
                if (i-j > dp[i-j])
                    b = i-j;

                max = Math.max(max, a*b);
            }
            dp[i]=max;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(3));
    }
}
