package com.company.src.dynamicProgramming.stock;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * 示例 2:
 *
 * 输入: prices = [1]
 * 输出: 0
 *
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class BestTimeToBuyAndSellStockWithCooldown309 {


    /**
     * 动态规划：模版https://labuladong.github.io/algo/3/28/96/
     * dp[i][0]当天交易完毕持有0只股票：dp[i][0] = max(前一天持有0只股今日不操作，前一天持有1只今日卖出 )
     * dp[i][1]当天交易完毕持有1只股票：dp[i][1] = max(前一天持有1只股今日不操作，前一天是0只股的冷冻期(即前2天有0只股)今日买入一只)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len==1)
            return 0;
        int[][] dp = new int[len][2]; // 可以空间优化成三个值
        dp[0][0] =0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]); //不操作/卖出
            if (i==1){
                dp[i][1] = Math.max(dp[i-1][1], -prices[i]); //第二天持有一只：第一天买入或者第二天买入
            }else
                dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0]-prices[i]); // 不操作/买入(买入的话有一天的冷冻期，所以需要是前2天的没有持股票的情况)
        }
        return dp[len-1][0];
    }

}
