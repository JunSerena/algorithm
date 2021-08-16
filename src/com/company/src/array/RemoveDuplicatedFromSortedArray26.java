package com.company.src.array;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 */
public class RemoveDuplicatedFromSortedArray26 {

    /**
     * [0,k)上都为非重复值
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int k = 1;
        for (int i=1; i<len;i++){
            if (nums[k-1]!=nums[i]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}
