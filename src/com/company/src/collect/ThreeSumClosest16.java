package com.company.src.collect;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 *
 * 返回这三个数的和。
 *
 * 假定每组输入只存在恰好一个解。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 示例 2：
 *
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 *
 * 链接：https://leetcode.cn/problems/3sum-closest/
 */
public class ThreeSumClosest16 {


    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[] {-1,2,1,-4}, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); //先排序
        int dif= Integer.MAX_VALUE;
        int result = 0;
        int len = nums.length;
        for (int i = 0; i <len ; i++) {

            int left = i+1, right = len-1, sum = 0;
            while (left<right){
                sum = nums[i]+nums[left]+nums[right];

                if (sum==target){   //如果相等的话，就不用再遍历了，直接返回target
                    return target;

                }else if (sum<target){
                    left++;
                }else {
                    right--;
                }
                int tmp = Math.min(Math.abs(sum - target), dif);
                if (dif > tmp){
                    result = sum;
                    dif = tmp;
                }

            }

        }

        return result;

    }

    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int result=nums[0]+nums[1]+nums[len-1];
        for (int i=0; i<len-2 ;i++){
            int leave = target-nums[i];
            int j=i+1, k=len-1;

            while (j<k){
                //result保存的是上一次的最小值。新一轮值更接近时
                int sum2 = nums[j]+nums[k];
                if (Math.abs(leave-sum2)< Math.abs(target- result)) {  //target-nums[i]-nums[j]-nums[k]<target- result
                    result=nums[i]+sum2;
                }

                //题目的意思应该是没有相等的情况
                if (sum2<leave){
                    j++;
                }else {
                    k--;
                }
            }

        }
        return result;

    }
}
