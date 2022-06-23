package com.company.src.collect;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * 219. 存在重复元素 II
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 *
 * 提示：
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 *
 * 链接：https://leetcode.cn/problems/contains-duplicate-ii/
 */
public class ContainDuplicate2_219 {
    public static void main(String[] args) {
        int[] nums = {99,99};
        System.out.println(containsNearbyDuplicate(nums,2));
    }

    /**
     * 利用滑动窗口加查找表方法，在窗口右侧值作为基准值，窗口左侧值放入表中，窗口最大宽度为k,窗口外的都从表中移除
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k<=0 ) return false; //题目需要不同索引。但是给的k范围又可为0。

        HashMap<Integer, Integer> map = new HashMap<>();

        // [left,i)窗口，i-left<=k
        for (int i = 0, left=0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                return true;
            }
            map.put(nums[i],i);

            if (i-left==k){
                map.remove(nums[left]);
                left++;
            }
        }

        return false;

    }
}
