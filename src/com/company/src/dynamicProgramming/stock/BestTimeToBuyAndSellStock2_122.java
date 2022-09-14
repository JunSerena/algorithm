package com.company.src.dynamicProgramming.stock;

/**
 * 股票系列二：
 * 122. 买卖股票的最佳时机 II
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 *
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 *
 * 返回 你能获得的 最大 利润 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 *      总利润为 4 + 3 = 7 。
 * 示例 2：
 *
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 *      总利润为 4 。
 * 示例 3：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
 *
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimeToBuyAndSellStock2_122 {

    /**
     * 解法一：收集每次上涨区间的和。因为不限制交易次数。
     * 相当于，每次买入，第二天是上涨的就卖出。如果第三天是上涨，就默认第二天买入了，第三天是下跌的，就默认没买。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int profit = 0;
        for (int i = 0; i < len-1; i++) {
            profit += prices[i+1]>prices[i]? prices[i+1]-prices[i]:0;
        }

        return profit;
    }

    /**
     * 解法二：动态规划。
     * 两种状态：交易结束后 当天持有有一只股票，当天无持股。
     * 当天无持股：前一天还有，今天卖出；或者前一天没有，今天也没买。
     * 当天持有一只股票：前一天还有，今天没卖；或者前一天没有，今天买入。
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2]; //dp[i][0] 无持股， dp[][1]持1股
        dp[0][0] =0; dp[0][1] = -prices[0];

        for (int i = 1; i <len ; i++) {
            dp[i][0] = Math.max(prices[i]+dp[i-1][1], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);
        }
        return Math.max(dp[len-1][0],dp[len-1][1]);
    }

    // 解法二空间优化，不需要数组，只需要前一天的 两种情况的收益
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i <len ; i++) {
            int tmp0 = Math.max(prices[i]+dp1, dp0);
            int tmp1 = Math.max(dp1, dp0-prices[i]);
            dp0 = tmp0;
            dp1 = tmp1;
        }
        return Math.max(dp0,dp1);

    }
}
