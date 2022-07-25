package com.company.src.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 */
public class Subsets90 {

    public static void main(String[] args) {
        int[] nums = new int[]{4,4,4,1,4};
        Subsets90 ins = new Subsets90();
        System.out.println(ins.subsetsWithDup(nums));
    }

    /**
     * 解法一：类似47号问题，先排序，固定重复元素的出现次序
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums==null||nums.length==0)
            return ret;
        visited = new boolean[nums.length];

        Arrays.sort(nums); //先排序
        for (int i = 0; i <=nums.length ; i++) {
            getKSub(nums, 0, i, new ArrayList<>());
        }
        return ret;
    }

    boolean[] visited;
    List<List<Integer>> ret = new ArrayList<>();

    public void getKSub(int[] nums, int index, int k, List<Integer> ans){
        if (k==0){
            ret.add(new ArrayList<>(ans));
            return;
        }

        for (int i = index; i <= nums.length-k ; i++) {
            if (visited[i] || (i>0&&nums[i]==nums[i-1]&&!visited[i-1]))
                continue;
            visited[i] = true;
            ans.add(nums[i]);
            getKSub(nums, i+1, k-1, ans);
            visited[i] = false;
            ans.remove(ans.size()-1);
        }
    }

}
