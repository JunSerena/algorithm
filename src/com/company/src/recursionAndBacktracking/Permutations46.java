package com.company.src.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * https://leetcode.cn/problems/permutations/
 */
public class Permutations46 {

    public static void main(String[] args) {
        Permutations46 ins = new Permutations46();
        int[] nums = new int[]{1,2,3};
        System.out.println(ins.permute(nums));
    }


    /**
     * 解法一：回溯。用一个数组保存该元素是否被访问。递归出来以后，需要把该元素的访问状态复原
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        visited = new int[nums.length];
        dfs(nums);
        return res;
    }
    int[] visited; // 下标为i的数组表示已经使用过了。
    List<Integer> ans = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    public void dfs(int[] nums){
        if (ans.size()==nums.length){
            res.add(new ArrayList<>(ans)); //注意一定要拷贝。
            return;
        }

        for (int i = 0; i <nums.length ; i++) {
            if (visited[i]!=1){
                ans.add(nums[i]);
                visited[i]=1;
                dfs(nums);
                visited[i]=0; //递归出来以后复原
                ans.remove(ans.size()-1);
            }
        }
    }

}
