package com.company.src.collect;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 220. 存在重复元素 III
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 *
 * 链接：https://leetcode.cn/problems/contains-duplicate-iii/
 */
public class ContainDuplicate3_220 {

    // 滑动窗口+查找表(有序集合)
    /**
     *
     * 核心思想： 对于元素 x，当我们希望判断滑动窗口中是否存在某个数 y 落在区间 [x - t, x + t] 中，只需要判断滑动窗口中所有大于等于 x - t 的元素中的最小元素是否小于等于 x + t 即可。
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> treeSet = new TreeSet<>();
        if (k<=0) return false;

        //[left, i)滑动窗口 i-left<=k
        for (int i = 0; i < nums.length; i++) {

            Long ceiling = treeSet.ceiling((long)nums[i]-(long)t);
            if (  ceiling!= null && ceiling <= (long)nums[i] + (long)t ){
                return true;
            }

            treeSet.add((long)nums[i]);
            if (i-k >= 0){ //保持滑动窗口大小为k
                treeSet.remove((long)nums[i-k]);
            }
        }

        return false;

    }

}
