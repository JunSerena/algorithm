package com.company.src.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 *
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 * 链接：https://leetcode.cn/problems/4sum/
 */
public class FourSum18 {
    //[1,-2,-5,-4,-3,3,3,5]
    //-11
    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{0,0,0,1000000000,1000000000,1000000000}, 1000000000));
    }

    // 在三数和前面套一个for循环。也就是说，先排序，再遍历nums[i],nums[j]，剩下两数用双指针去找。
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        int len = nums.length;
        if (len<3)  return list;

        Arrays.sort(nums); //先排序

        for (int i = 0; i <len-3 ; i++) {

            if (i>0 && nums[i]==nums[i-1]){
                continue; //对第一个值去重。
            }

            //减枝优化
            //若是i开始连续的四个数都大于target,那肯定没有满足情况的数，直接跳出。
            if((long)nums[i]+(long)nums[i+1]+(long)nums[i+2]+(long)nums[i+3]>target)
                break;
            //若是i和最大的三个数相加都比target小，那说明i太小了，直接跳过这一个值往下走
            if((long)nums[i]+(long)nums[len-1]+(long)nums[len-2]+(long)nums[len-3]<target)
                continue;

            for (int j = i+1; j <len-2 ; j++) {
                // 当nums[i]和nums[j] 固定的时候， 移动左右边界，使得左右边界之和等于target-nums[i]-nums[j]
                int left = j+1;
                int right = len-1;

                if ( j!=i+1 && nums[j]==nums[j-1]){
                    continue; //对第二个值去重。
                }


                //减枝优化
                //若是nums[i]加上 j开始连续的三个数都大于target,那肯定没有满足情况的数，直接跳出。
                if((long)nums[i]+(long)nums[j]+(long)nums[j+1]+(long)nums[j+2]>target)
                    break;
                //若是i、j和最大的2个数相加都比target小，那说明j太小了，直接跳过这一个值往下走
                if((long)nums[i]+(long)nums[j]+(long)nums[len-1]+(long)nums[len-2]<target)
                    continue;


                while (left< right) {
                    long sum = (long)nums[i]+ (long)nums[j]+ (long)nums[left]+ (long)nums[right];  //注意此处要用long
                    if (sum==target){
                        list.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        left++;
                        right--;
                        while (left<right && nums[left-1]==nums[left]){  //对第三个值去重
                            left++;
                        }
                        while (left<right && nums[right+1]==nums[right]){ //对第四个值去重
                            right--;
                        }

                    }else if (sum < target){
                        left++;
                    }else {
                        right --;
                    }
                }
            }


        }
        return list;

    }

}
