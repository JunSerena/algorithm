package com.company.src.collect;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 链接：https://leetcode.cn/problems/two-sum/
 */
public class TwoSum1 {

    //利用查找表方法
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);  //直接覆盖旧索引
        }


        for (int i = 0; i < nums.length; i++) {
            int a = target-nums[i];

            if (map.get(a)!=null && map.get(a)!=i){
                return new int[]{i, map.get(a)};
            }
        }

        return null;
    }

    // 另一种查找表的思路：只把x值左边的元素放入map中，只从左边找。可以省一点空间和时间
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i],i);  //直接覆盖旧索引
//        }

        for (int i = 0; i < nums.length; i++) {
            int a = target-nums[i];

            if (map.get(a)!=null){
                return new int[]{i, map.get(a)};
            }
            map.put(nums[i],i);
        }

        return null;
    }
}
