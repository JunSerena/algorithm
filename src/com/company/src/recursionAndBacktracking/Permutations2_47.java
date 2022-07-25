package com.company.src.recursionAndBacktracking;


import com.company.src.linkedList.util.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 链接：https://leetcode.cn/problems/permutations-ii/
 */
public class Permutations2_47 {
    public static void main(String[] args) {
        Permutations2_47 ins = new Permutations2_47();
        int[] nums = new int[]{1,1,1,2};
        List<List<Integer>> list = ins.permuteUnique2(nums);
        System.out.println(list);
    }

    /**
     * 解法一：回溯法，用Set来保存已排列元素 以便去重。
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums==null || nums.length==0)
            return new ArrayList<>();
        visited=new int[nums.length];
        dfs(nums);
        return new ArrayList<>(ret);
    }

    int[] visited;
    List<Integer> ans = new ArrayList<>();
    Set<List<Integer>> ret = new HashSet<>();
    public void dfs(int[] nums){
        if (ans.size() == nums.length){
            ret.add(new ArrayList<>(ans));
            return;
        }

        for (int i = 0; i <nums.length ; i++) {
            if (visited[i]!=1){
                visited[i]=1;
                ans.add(nums[i]);
                dfs(nums);
                visited[i]=0;
                ans.remove(ans.size()-1);
            }
        }
    }

    /**
     * 解法二：先排序。让相等的元素相邻。
     * 回溯时 先固定相等的元素在 结果中的相对位置。比如 1 1 1 2，假设前三个1为1a 1b 1c，无论2放在哪个位置，1的相对位置保证不变，
     * 结果必然为 [1a 1b 1c 2], [1a 1b 2 1c], [1a 2 1b 1c], [2 1a 1b 1c]等
     * @param nums
     * @return
     */
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique2(int[] nums) {
        if (nums==null || nums.length==0)
            return res;
        Arrays.sort(nums);
        visited=new int[nums.length];
        findPermute(nums);
        return res;
    }

    public void findPermute(int[] nums){
        if (ans.size() == nums.length){
            res.add(new ArrayList<>(ans));
            return;
        }

        for (int i = 0; i <nums.length ; i++) {

            // visited[i]==1 访问过的元素需跳过。
            // i>0 && nums[i]==nums[i-1] && visited[i-1]==0： 这句话可以翻译为 遇到相同的节点 要填后面的节点就一定先填过前面的节点
            //  假设三个1 分别为 1a 1b 1c 要想填1b 就一定之前填过1a 要想填过1c 就一定填过1b 那么三个1都被填充的顺序就一定为1a 1b 1c
            // 同理：visited[i-1]==1 顺序就是 1c 1b 1a
            if (visited[i]==1 || (i>0&&nums[i]==nums[i-1]&&visited[i-1]==0)){  //visited[i-1]==0前面元素没有访问过，所以不行，跳过。
                continue;
            }

            visited[i] = 1;
            ans.add(nums[i]);
            findPermute(nums);
            visited[i] = 0;
            ans.remove(ans.size() - 1);

        }
    }

}
