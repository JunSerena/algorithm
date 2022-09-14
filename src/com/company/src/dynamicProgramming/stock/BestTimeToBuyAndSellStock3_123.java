package com.company.src.dynamicProgramming.stock;

/**
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2：
 *
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 示例 4：
 *
 * 输入：prices = [1]
 * 输出：0
 *
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuyAndSellStock3_123 {

    public static void main(String[] args) {
        int[] price = {3,3,5,0,0,3,1,4};
        //int[] price = {1,2,3,4,5};
        System.out.println(maxProfit(price));
    }

    // 动态规划：模版 https://labuladong.github.io/algo/3/28/96/
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 1)
            return 0;
        int[][][] dp = new int[len][2][2]; // 最多两次交易，两种股票状态
        dp[0][0][0] = 0;
        dp[0][0][1] = -prices[0]; //一次交易，1只股票
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0]; //限制2次交易，1只股票

        for (int i = 1; i < len; i++) {
            dp[i][0][0] = Math.max(dp[i-1][0][0], dp[i-1][0][1]+prices[i]); // 没买/卖了一只
            dp[i][0][1] = Math.max(dp[i-1][0][1], -prices[i]); //之前有，没操作 / 之前无，买了一只
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1]+prices[i]); // 没买/卖了一只
            dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][0]-prices[i]); //之前有，没操作 / 之前卖出1次，买了一只
        }

        return Math.max(dp[len-1][0][0], dp[len-1][1][0]);

    }
}
