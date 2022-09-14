package com.company.src.dynamicProgramming;

import com.company.src.stackAndQueue.util.TreeNode;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 *
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 *
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * 示例 2:
 *
 *
 *
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 *
 * 链接：https://leetcode.cn/problems/house-robber-iii/
 */
public class HouseRobber3_337 {
    /**
     * 解法一：动态规划。
     *  f(o)表示选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；
     *      o选中的情况下，子树上被选中的节点最大和为：g(l)+g(r), 注意需要加上父节点o的值，毕竟o是被选中的。
     *
     *  g(o) 表示不选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；
     *      不选中o，则子树最大值为两者之和， g(o)=max{f(l), g(l)} + max{f(r), g(r)}.
     *      即左子树上可以选择左孩子也可以选择不选中左孩子，右子树上也是。
     *
     * @param root
     * @return
     */
    Map<TreeNode, Integer> f = new HashMap<>();
    Map<TreeNode, Integer> g = new HashMap<>();
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.get(root), g.get(root));
    }

    // 后序遍历
    public void dfs(TreeNode root){
        if (root==null)
            return;
        if (root.left==null && root.right==null){
            f.put(root, root.val);
            g.put(root, 0);
            return;
        }

        dfs(root.left);
        dfs(root.right);

        // 计算f(o)
        f.put(root, root.val + g.getOrDefault(root.left,0)+g.getOrDefault(root.right, 0));

        // 计算g(o)
        int left = Math.max(f.getOrDefault(root.left,0), g.getOrDefault(root.left, 0));
        int right = Math.max(f.getOrDefault(root.right,0), g.getOrDefault(root.right, 0));
        g.put(root, left+right);

    }

    public static void main(String[] args) {
        HouseRobber3_337 houseRobber = new HouseRobber3_337();
        int[] arr = {2,1,3,-1,4};
        System.out.println(houseRobber.rob(TreeNode.buildTree(arr)));
    }
}
