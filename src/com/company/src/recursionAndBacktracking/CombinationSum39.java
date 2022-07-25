package com.company.src.recursionAndBacktracking;


import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 *
 *
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例 2：
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 *
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 * 链接：https://leetcode.cn/problems/combination-sum/
 */
public class CombinationSum39 {


    public static void main(String[] args) {
        int[] arr = new int[]{2,3,6,7};
        CombinationSum39 ins = new CombinationSum39();
        System.out.println(ins.combinationSum(arr, 7));
    }




    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates==null || candidates.length==0)
            return new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>());
        return res;
    }

    List<List<Integer>> res = new ArrayList<>();

    // 递归存在两种选择，一种是从当前索引(即重复利用当前值)，一种是跳过当前索引。
    // target-当前值==0时加入结果中
    public void dfs(int[] candidates, int target, int index,  List<Integer> ans){
        // 需要及时停止递归
        if (index==candidates.length)
            return;

        if (target==0){
            res.add(new ArrayList<>(ans));
            return;
        }
        if (target<0)
            return;

        // 直接跳过
        dfs(candidates, target, index+1, ans);


        if (candidates[index]<=target){
            ans.add(candidates[index]);
            target = target-candidates[index];
            dfs(candidates, target, index, ans); //不跳过
            ans.remove(ans.size()-1);
        }

    }

//    /**
//     * 解法一：会超时，废弃
//     * @param candidates
//     * @param target
//     * @param sum
//     * @param ans
//     */
//    public void dfs(int[] candidates, int target,  int sum, List<Integer> ans){
//        if (sum==target){
//            List<Integer> list = new ArrayList<>(ans);
//            Collections.sort(list);
//            res.add(list);
//            return;
//        }
//        if (sum>target){
//            return;
//        }
//
//        for (int i = 0; i < candidates.length; i++) {
//            if (candidates[i]>target)
//                continue;
//            ans.add(candidates[i]);
//            sum += candidates[i];
//            dfs(candidates, target, sum, ans);
//            ans.remove(ans.size()-1);
//            sum -= candidates[i];
//        }
//    }
}
