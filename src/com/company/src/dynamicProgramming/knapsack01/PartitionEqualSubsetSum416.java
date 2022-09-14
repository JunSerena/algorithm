package com.company.src.dynamicProgramming.knapsack01;

import com.company.src.recursionAndBacktracking.PacificAlanticWaterFlow417;
import com.sun.xml.internal.ws.wsdl.writer.document.Part;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 * 链接：https://leetcode.cn/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum416 {

    public static void main(String[] args) {
        //int[] nums = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        int[] nums = {2,3,1,2};
        PartitionEqualSubsetSum416 ins = new PartitionEqualSubsetSum416();
        System.out.println(ins.canPartition3(nums));
    }

    /**
     * 递归，但是会超时，所以可改记忆化搜索
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum =0;
        int len = nums.length;
        for (int num : nums) {
            sum += num;
        }
        // 不是偶数根本无法拆分成两相同的数之和
        if (sum%2==1)
            return false;
        int cap = sum/2; //直接除以2会丢掉末尾小数

        memo = new int[len][cap+1];
        return fullPackage2(nums, len-1, cap );
    }

    public boolean fullPackage(int[] nums, int index, int c){
        // 边界条件
        if (index<0||c<0)
            return false;
        if (c==0 ||nums[index]==c)
            return true;
        return fullPackage(nums, index-1, c)|| fullPackage(nums,index-1, c-nums[index]);

    }


    //解法2：记忆化搜索。数组用int,0表示未计算，1表示true,2 表示false
    int[][] memo;
    public boolean fullPackage2(int[] nums, int index, int c){
        // 边界条件
        if (index<0||c<0)
            return false;
        if (c==0 ||nums[index]==c)
            return true;
        if (memo[index][c]==0)
            memo[index][c] =
                fullPackage2(nums, index-1, c)
                        || fullPackage2(nums,index-1, c-nums[index]) ? 1 : 2;

        return  memo[index][c]==1;

    }



    /**
     * 解法三：背包问题。在N个物品中找出选出一定物品填满sum/2的容量。
     * F(i,C) = V[i] + F(i-1, C-V[i])
     * @param nums
     * @return
     */
    public boolean canPartition3(int[] nums) {
        int sum =0;
        int len = nums.length;
        for (int i = 0; i <len ; i++) {
            sum += nums[i];
        }
        // 不是偶数根本无法拆分成两相同的数之和
        if (sum%2==1)
            return false;
        int cap = sum/2;

        boolean[][] dp = new boolean[len][cap+1]; //考虑[0,index]中 抽出几个 刚好大小为c
        // 第0号物品
        for (int i = 0; i <=cap ; i++) {
            dp[0][i] = nums[0]==i; // 刚好放下，才为true，否则为false
        }

        // 谁也不放进去，背包为0
        for (int i = 0; i <len ; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= cap ; j++) {

                dp[i][j] = dp[i-1][j]; // i不放进去
                if (j>=nums[i]){
                    //          i不放进去       i放进去
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
        }


        return dp[len-1][cap];
    }


    //解法四，优化空间，只保留一行数组
    public boolean canPartition4(int[] nums) {
        int sum =0;
        int len = nums.length;
        for (int i = 0; i <len ; i++) {
            sum += nums[i];
        }
        // 不是偶数根本无法拆分成两相同的数之和
        if (sum%2==1)
            return false;
        int cap = sum/2;
        
        boolean[] dp = new boolean[cap+1];
        dp[0] = true; // 背包容量为0，啥也不装，就是true

        for (int i = 0; i < len; i++) { //考虑从[0, i]中取元素能否刚好填满 容量为j的背包
            for (int j = cap; j >=nums[i]; j--) { // 容量接着变小，放不下nums[i]了
                dp[j] = dp[j] || dp[j-nums[i]];
            }
            
        }
        return dp[cap];
    }
}
