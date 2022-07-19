package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 404. 左叶子之和
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: 24
 * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * 示例 2:
 *
 * 输入: root = [1]
 * 输出: 0
 */
public class SumOfLeftLeaves404 {

    // 递归分解，左叶子加上右子树的和
    public int sumOfLeftLeaves(TreeNode root) {
        if (root==null)
            return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left!=null ){
            if (left.left==null && left.right==null){
                return left.val + sumOfLeftLeaves(right);
            }else
                return  sumOfLeftLeaves(left)+ sumOfLeftLeaves(right);

        }else
            return sumOfLeftLeaves(right);
    }


    // 官方递归写法：更优雅
    public int sumOfLeftLeaves2(TreeNode root) {
        return root != null ? dfs(root) : 0;
    }

    public int dfs(TreeNode node) {
        int ans = 0;
        if (node.left != null) { //左子树
            ans += isLeafNode(node.left) ? node.left.val : dfs(node.left); //是叶子节点就求和，非叶子节点就递归
        }
        if (node.right != null && !isLeafNode(node.right)) { //右子树，右孩子不为空且不是叶子节点
            ans += dfs(node.right);
        }
        return ans;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }


    /**
     * bfs广度优先搜索
     * @param root
     * @return
     */
    public int sumOfLeftLeaves3(TreeNode root) {
        if (root==null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                TreeNode right = node.right;
                if (left!=null ){
                    if (isLeafNode(left))
                        result += left.val;
                    else
                        queue.add(left);
                }

                if (right!=null ){
                    queue.add(right);
                }
            }
        }
        return result;
    }



}
