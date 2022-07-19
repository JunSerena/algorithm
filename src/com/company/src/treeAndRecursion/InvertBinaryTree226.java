package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

/**
 * 226. 翻转二叉树
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * 示例 2：
 *
 *
 *
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 * 链接：https://leetcode.cn/problems/invert-binary-tree/
 */
public class InvertBinaryTree226 {
    public TreeNode invertTree(TreeNode root) {
        if (root==null)
            return null;


        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    //官方解答类似
    public TreeNode invertTree2(TreeNode root) {
        if (root==null)
            return null;

        TreeNode left = invertTree2(root.left);  //先翻转左右子树
        TreeNode right = invertTree2(root.right);
        root.left = right; //再翻转根节点
        root.right = left;

        return root;
    }

}
