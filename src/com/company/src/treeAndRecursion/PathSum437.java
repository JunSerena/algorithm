package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示：5,3   5,2,1   -3,11
 * 示例 2：
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 * 链接：https://leetcode.cn/problems/path-sum-iii/
 */
public class PathSum437 {
    public int pathSum(TreeNode root, int targetSum) {

        // 两种情况，本节点在路径上和本节点不在路径上
        int count = 0;

        if (root==null){
            return count;
        }
        // 包含当前root节点且其和为sum的路径。
        count+=findPath(root, targetSum);

        // 不包含当前节点，且其和为sum的路径
        count+=pathSum(root.left, targetSum);
        count+=pathSum(root.right, targetSum);

        return count;
    }

    // 本节点在路径上
    public int findPath(TreeNode node, int sum){
        int count =0;
        if (node==null){
            return count;
        }
        if (node.val==sum){
            count++;
           // return count;  //注意：此处一定不要return，因为就算此处已经等于sum了，但是后续的孩子节点相加可能会等于0，因为有负数。
            // 比如树[1,-2,-3,1,3,-2,null,-1] sum=-1. 路径：（1,-2）已经是一条路径，但是后续还有（1,-2,1,-1）。如果return就会丢失后面那条路径
        }
        count += findPath(node.left, sum-node.val) + findPath(node.right, sum-node.val);
        return count;
    }
}
