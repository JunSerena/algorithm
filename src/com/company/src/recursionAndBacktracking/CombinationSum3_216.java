package com.company.src.recursionAndBacktracking;

import com.company.src.linkedList.util.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 *
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 *
 *
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 解释:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * 没有其他符合的组合了。
 * 示例 3:
 *
 * 输入: k = 4, n = 1
 * 输出: []
 * 解释: 不存在有效的组合。
 * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
 *
 * 链接：https://leetcode.cn/problems/combination-sum-iii/
 */
public class CombinationSum3_216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k>9||n<0)
            return ret;
        dfs(k,n,1,new ArrayList<>());
        return ret;
    }

    List<List<Integer>> ret = new ArrayList<>();
    public void dfs(int k, int n, int index, List<Integer> ans){
        if (n==0){
            if (ans.size()==k){
                ret.add(new ArrayList<>(ans));
            }
            return;
        }
        if (index>9)
            return;

        // [i, 9]之间必须要有 k-ans.size()个元素 9-i+1>=k-ans.size  所以i <= 9-(k-ans.size())+1
        for (int i = index; i <= 10-(k-ans.size()); i++) {
            if (i>n)
                return;
            ans.add(i);
            dfs(k,n-i, i+1, ans);
            ans.remove(ans.size()-1);
        }
    }
}
