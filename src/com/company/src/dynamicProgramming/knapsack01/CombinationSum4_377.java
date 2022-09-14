package com.company.src.dynamicProgramming.knapsack01;

import java.util.Arrays;

/**
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 *
 * 题目数据保证答案符合 32 位整数范围。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 *
 * 输入：nums = [9], target = 3
 * 输出：0
 *
 * 链接：https://leetcode.cn/problems/combination-sum-iv/
 */
public class CombinationSum4_377 {

    /**
     * 解法一：递归加记忆化搜索。只要知道最后一个元素x，那么F(target) = F(target-x).
     * 因为不知道最后一个元素是谁，所以需要遍历数组。
     */
    public int combinationSum4(int[] nums, int target) {
        int[] memo = new int[target+1];
        Arrays.fill(memo, -1); // 初始化为-1，表示未经计算的元素
        return combinate(nums, target, memo);
    }

    public int combinate(int[] nums, int target, int[] memo){
        if (target==0){ // 有1种情况
            return 1;
        }
        if (target<0){
            return 0;
        }

        if (memo[target]!=-1){
            return memo[target];
        }

        int len = nums.length;
        int count =0;
        for (int i = 0; i <len ; i++) {
            int res = combinate(nums, target-nums[i], memo);
            if (res!=0){
                count+=res;
            }
        }
        memo[target] = count;
        return count;
    }

    // dp[x]表示为和为x时有多少种情况
    public int combinationSum4_2(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0]=1; // 1种情况。
        int len = nums.length;

        for (int i = 0; i <=target; i++) {
            for (int j = 0; j <len ; j++) {
                if (nums[j] <= i && dp[i-nums[j]]!=0){
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }
}
