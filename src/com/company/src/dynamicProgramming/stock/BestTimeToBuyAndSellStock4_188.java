package com.company.src.dynamicProgramming.stock;

/**
 * 188. 买卖股票的最佳时机 IV
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 *
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class BestTimeToBuyAndSellStock4_188 {

    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len==0 || len==1)
            return 0;
        int[][][] dp = new  int[len][k+1][2]; //k交易次数,需要从0开始

        //0次交易的情况
        for (int i = 0; i <=len; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }


        for (int i = 0; i <len ; i++) {
            for (int j = 1; j <= k; j++) {
                if (i==0){
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[0];
                }else {
                    dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1]+prices[i]);
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0]-prices[i]);
                }
            }
        }
        return dp[len-1][k][0];

    }
}
