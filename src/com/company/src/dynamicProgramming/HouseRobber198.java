package com.company.src.dynamicProgramming;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 链接：https://leetcode.cn/problems/house-robber/
 */
public class HouseRobber198 {
    // 状态：考虑偷[x, n-1]范围里的房子，F(x) 所能获得的最大收益
    // 状态转移：F(0) = max{ v0+F(2), v1+F(3), ... , vn-3+F(n-1), vn-2,  vn-1 }
    // F(n) = vn-1
    // F(n-1) = max{vn-2, vn-1}
    public int rob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[len-1] = nums[len-1];
        if (len==1)
            return nums[len-1];

        dp[len-2] = Math.max(nums[len-2], nums[len-1]);
        int max = dp[len-2];
        for (int i = len-3; i >=0 ; i--) {
            max = Math.max(max, nums[i]+dp[i+2]);
            dp[i] = max;
        }

        return dp[0];
    }

    /**
     * 动态规划+记忆化搜索
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        memo = new int[nums.length];
        // 默认为0的话，如果给出的数组是[0,0,0,0...0,0,0]，会超时。
        for (int i = 0; i < nums.length; i++) {
            memo[i] =-1;
        }
        return tryRob(nums, 0);
    }
    int[] memo;

    // 考虑偷取 [index, n-1]处的房子能带来的最大收益
    public int tryRob(int[] nums, int index) {

        if (index>=nums.length)
            return 0;
        if (memo[index]!=-1)
            return memo[index];

        int res = 0;
        for (int i = index; i <nums.length ; i++) {
            res = Math.max(res, nums[i]+tryRob(nums,i+2));
        }
        memo[index] = res;
        return res;
    }


}
