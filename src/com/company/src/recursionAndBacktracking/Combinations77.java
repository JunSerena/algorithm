package com.company.src.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 示例 2：
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 * 链接：https://leetcode.cn/problems/combinations/
 */
public class Combinations77 {

    public static void main(String[] args) {
        Combinations77 ins = new Combinations77();
        System.out.println(ins.combine(4,2));

    }

    /**
     * 解法一：回溯。记录当前索引index，每次只取index后的值
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        if (k>n)
            return res;
        dfs(n,k,1,new ArrayList<>());
        return res;
    }


    List<List<Integer>> res = new ArrayList<>();


    // index表示下一个需要取的元素位置
    public void dfs(int n, int k, int index, List<Integer> ans) {
        if (ans.size()==k){
            res.add(new ArrayList<>(ans));
            return;
        }

        for (int i = index; i <=n ; i++) {
            ans.add(i);
            dfs(n,k,i+1, ans);
            ans.remove(ans.size()-1);
        }

    }

    /**
     * 优化剪枝
     */
    public void dfs2(int n, int k, int index, List<Integer> ans) {
        if (ans.size()==k){
            res.add(new ArrayList<>(ans));
            return;
        }

        // [i,n]中至少要有 k-ans.size个元素
        for (int i = index; i <= n -(k-ans.size())+1 ; i++) {
            ans.add(i);
            dfs(n,k,i+1, ans);
            ans.remove(ans.size()-1);
        }
    }

}
