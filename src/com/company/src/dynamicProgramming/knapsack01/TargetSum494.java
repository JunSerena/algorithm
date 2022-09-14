package com.company.src.dynamicProgramming.knapsack01;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 *
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 * 链接：https://leetcode.cn/problems/target-sum/
 */
public class TargetSum494 {
    /**
     * 数学解法，x+y=sum，x-y=target, 所以x=(sum+target)/2
     * 其中x为前面添加+,y为前面添加-。即问题转化成 从nums[]中找出和为x的 情况数。
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int a: nums  ) {
            sum += a;
        }

        int targetSum = (sum+target)/2;
        // targetSum必须大于等于0，因为是正数之和
        if (targetSum<0 || (sum+target)%2 != 0) //无法分割成 x、y两部分
            return 0;
        
        //背包问题
        int len = nums.length;
        int[][] dp = new int[len][targetSum+1];

        // base情况
        dp[0][0] = 1; //第一个元素不包含在X中。（元素和为0，不包含任何元素）
        for (int i = 0; i <len ; i++) {
            for (int j = 0; j <= targetSum ; j++) {
                if (i==0){
                    dp[i][j] += nums[i]==j?1:0; //第一个元素包含在X中
                    continue;
                }
                dp[i][j] = dp[i-1][j]; //不包含自己
                if (nums[i]<=j)
                    dp[i][j] += dp[i-1][j-nums[i]]; //包含自己
            }
        }
        return dp[len-1][targetSum];
    }


    // dp换个写法，比较容易理解
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int a: nums  ) {
            sum += a;
        }

        int targetSum = (sum+target)/2;
        // targetSum必须大于等于0，因为是正数之和
        if (targetSum<0 || (sum+target)%2 != 0) //无法分割成 x、y两部分
            return 0;

        //背包问题
        int len = nums.length;
        int[][] dp = new int[len+1][targetSum+1]; //第一个维度取len+1。比较好处理nums[0]的问题

        // base情况
        dp[0][0] = 1; //当不包含元素时，元素和为0，所以有一种情况
        for (int i = 1; i <=len ; i++) {
            for (int j = 0; j <= targetSum ; j++) {
                dp[i][j] = dp[i-1][j]; //不包含自己
                if (nums[i-1]<=j)
                    dp[i][j] += dp[i-1][j-nums[i-1]]; //包含自己
            }
        }
        return dp[len][targetSum];
    }

    public static void main(String[] args) {
        int[] nums = {0,0,0,0,0,0,0,0,1};
        //int[] nums = {100};
        TargetSum494 ins = new TargetSum494();
        System.out.println(ins.findTargetSumWays2(nums,1));
    }
}
