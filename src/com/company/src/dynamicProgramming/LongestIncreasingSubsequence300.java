package com.company.src.dynamicProgramming;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 * 链接：https://leetcode.cn/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence300 {
    /**
     * 动态规划：F(i)表示以i元素结尾的最长子串长度。那么转移方程如下：
     * F(i) = max(F(j)+1) 当nums[j]<nums[i]，j<i时。
     * 最后并不能确定是以哪一个元素结尾可以得到最长子串，因此要遍历F(x)结果，找到最大值
     *
     * 时间复杂度：O(n2)
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp,1); // 默认是1，因为自身就是长度为1的递增子序列

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <i ; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], 1+dp[j]); //nums[i]更大，可以构成一个更长的子序列，所以+1
            }
        }
        int max = 1;
        for (int x: dp) {
            max = Math.max(max,x);
        }
        return max;
    }
}
