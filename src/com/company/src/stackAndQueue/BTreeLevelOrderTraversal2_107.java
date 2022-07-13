package com.company.src.stackAndQueue;

import com.company.src.stackAndQueue.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. 二叉树的层序遍历 II
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[15,7],[9,20],[3]]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 * 链接：https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 */
public class BTreeLevelOrderTraversal2_107 {

    //就是102号题目的层次遍历，然后将那一层的列表插入到结果列表的头部
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode node = root;
        queue.add(node);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                list.add(node.val);
                if (node.left!=null) queue.add(node.left);
                if (node.right!=null) queue.add(node.right);
            }

            result.add(0, list);//把结果插入头部
        }

        return result;

    }
}
