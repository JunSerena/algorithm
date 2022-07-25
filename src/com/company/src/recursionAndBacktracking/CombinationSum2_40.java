package com.company.src.recursionAndBacktracking;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 40. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。
 *
 *
 *
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * 链接：https://leetcode.cn/problems/combination-sum-ii/
 */
public class CombinationSum2_40 {

    public static void main(String[] args) {
        int[] candidates = new int[]{10,1,2,7,6,1,5};

        CombinationSum2_40 ins = new CombinationSum2_40();
        ins.combinationSum2(candidates, 8);
        System.out.println(ins.res);
    }

    /**
     * 解法二：参照47号排列问题。先排序，然后固定相等元素的出现顺序。
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates==null || candidates.length==0)
            return res;
        Arrays.sort(candidates);
        visited = new boolean[candidates.length];
        dfs(candidates,target, 0, new ArrayList<>());
        return res;
    }

    List<List<Integer>> res = new ArrayList<>();
    boolean[] visited;

    public void dfs(int[] candidates, int target,int index, List<Integer> ans) {
        if (target==0){
            res.add(new ArrayList<>(ans));
            return;
        }
        if (target<0)
            return;

        for (int i = index; i <candidates.length ; i++) {
            if (candidates[i]>target)
                break;
            // 访问过的元素跳过       // 相等元素，必须排前面的先出现，否则跳过
            if (visited[i] || (i>0 && candidates[i]==candidates[i-1]&&!visited[i-1])){
                continue;
            }
            visited[i] = true;
            ans.add(candidates[i]);
            dfs(candidates, target-candidates[i],i+1, ans);
            ans.remove(ans.size()-1);
            visited[i] = false;
        }
    }


//    /**
//     * 解法一：普通搜索回溯。但是会超时。废弃。
//     * @param candidates
//     * @param target
//     * @return
//     */
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        if (candidates==null || candidates.length==0)
//            return new ArrayList<>();
//        Arrays.sort(candidates);
//        dfs(candidates,target, 0, new ArrayList<>());
//        return new ArrayList<>( res);
//    }
//
//    Set<List<Integer>> res = new HashSet<>();
//
//    public void dfs(int[] candidates, int target,int index, List<Integer> ans) {
//        if (target==0){
//            res.add(new ArrayList<>(ans));
//            return;
//        }
//        if (target<0)
//            return;
//
//        for (int i = index; i <candidates.length ; i++) {
//            if (candidates[i]>target)
//                break;
//            ans.add(candidates[i]);
//            dfs(candidates, target-candidates[i],i+1, ans);
//            ans.remove(ans.size()-1);
//        }
//    }
}
