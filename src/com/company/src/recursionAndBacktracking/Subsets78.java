package com.company.src.recursionAndBacktracking;

import com.company.src.linkedList.util.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 链接：https://leetcode.cn/problems/subsets/
 */
public class Subsets78 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        Subsets78 subsets78 = new Subsets78();
        System.out.println(subsets78.subsets(nums));
    }

    public List<List<Integer>> subsets(int[] nums) {
        if (nums==null || nums.length==0)
            return ret;
        // 子集中元素个数从0到num.length
        for (int i = 0; i <= nums.length; i++) {
            getKNums(nums, i, 0, new ArrayList<>());
        }
        return ret;
    }

    List<List<Integer>> ret = new ArrayList<>();
    public void getKNums(int[] nums, int k, int index, List<Integer> ans){
        if (k==0){
            ret.add(new ArrayList<>(ans));
            return;
        }

        // [i, len）选择 k 个 len-i >k
        for (int i = index; i <= nums.length-k ; i++) {
            ans.add(nums[i]);
            getKNums(nums, k-1, i+1, ans);  // index必须为i+1,而非index+1
            ans.remove(ans.size()-1);
        }

    }
}
