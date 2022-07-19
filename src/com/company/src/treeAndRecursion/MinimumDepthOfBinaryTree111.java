package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * 示例 2：
 *
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *
 * 链接：https://leetcode.cn/problems/minimum-depth-of-binary-tree/
 *
 */
public class MinimumDepthOfBinaryTree111 {
    /**
     * 解法：dfs，但是有陷阱： [2,null,3,null,4,null,5,null,6]最短有五层，不是1层。。。。也就是说root节点如果只有一个孩子需要注意
     * ps：路径一定是节点到叶子节点的长度。
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {

        if (root==null)
            return 0;
        else if (root.left==null){
            return minDepth(root.right)+1;
        }else if ((root.right==null)){
            return minDepth(root.left)+1;
        }

        return Math.min(minDepth(root.left), minDepth(root.right))+1;

    }

}
