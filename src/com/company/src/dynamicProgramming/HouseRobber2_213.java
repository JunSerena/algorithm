package com.company.src.dynamicProgramming;

/**
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 *
 * 输入：nums = [1,2,3]
 * 输出：3
 *
 * 链接：https://leetcode.cn/problems/house-robber-ii/
 */
public class HouseRobber2_213 {
    public static void main(String[] args) {
        HouseRobber2_213 houseRobber = new HouseRobber2_213();
        int[] nums = {232,98,226,19,55,45,172,61,19,147,207,192,189,9,42,165,195,118,37,22,227,92,126,86,167,239,171,172,39,96,57,214,153,205,18,63,41,220,227,4,197,236,84,115,186,237,182,226,6,10,215,19,175,63,46,49,95,5,239,167,3,212,206,48};
        System.out.println(houseRobber.rob(nums));
    }

    /**
     * 解法一：动态规划，可以将198题抽出一个计算从[start,end]处的的转移方程，表示考虑抢劫[start,end]处获得的最大收益
     * 首尾不能同时抢，就分开讨论，抢头，抢中间，抢尾
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        // 三种情况，抢头，抢尾，头尾都不抢
        int len = nums.length;
        if (len==1)
            return nums[0];
        if (len==2)
            return Math.max(nums[0], nums[1]);

        int[] dp = new int[len];
        int robMid = tryRob(nums, 1, len-2, dp); //首尾都不抢
        int robHead = Math.max(nums[0]+dp[2], robMid); //抢头部, robHead>=robMid

        int robTail = tryRob(nums,1, len-1, new int[len]); //抢尾部

        return Math.max(robHead, robTail);

    }

    /**
     * 解法一，优化，不需要分成三种，其实就是两种，抢头([0,n-2])和抢尾([1,n-1)，调用两次tryRob函数
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        // 2种情况，抢头，抢尾
        int len = nums.length;
        if (len==1)
            return nums[0];
        if (len==2)
            return Math.max(nums[0], nums[1]);

        int robHead = tryRob(nums, 0, len-2,  new int[len]); //抢头
        int robTail = tryRob(nums,1, len-1, new int[len]); //抢尾部

        return Math.max(robHead, robTail);

    }


    //考虑打劫[start, end]处的房子(不成环)
    public int tryRob(int[] nums, int start, int end, int[] dp) {
        if (end<start)
            return 0;
        if (end==start)
            return nums[start];
        dp[end] = nums[end];
        dp[end-1] = Math.max(nums[end-1], nums[end]);
        int max = dp[end-1]; //注意此处
        for (int i = end-2; i >=start ; i--) {
            max = Math.max(max, nums[i]+dp[i+2]);
            dp[i] = max;
        }
        return dp[start];
    }
}
