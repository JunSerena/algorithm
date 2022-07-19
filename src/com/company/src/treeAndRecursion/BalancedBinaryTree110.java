package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * 示例 3：
 *
 * 输入：root = []
 * 输出：true
 *
 * 链接：https://leetcode.cn/problems/balanced-binary-tree/
 */
public class BalancedBinaryTree110 {
    /**
     * 解法一：自顶向下递归。先判断根节点左右子树高度差，再递归判断左子树和右子树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root==null)
            return true;
        // 左右子树高度差大于1，返回false
        return (Math.abs(getDepth(root.left)-getDepth(root.right))<=1) && isBalanced(root.left)&& isBalanced(root.right);
    }

    public static int getDepth(TreeNode node){
        if (node==null)
            return 0;
        return Math.max(getDepth(node.left), getDepth(node.right))+1;
    }

    /**
     * 官方题解2：自底向上递归，避免多次计算子树的高度
     * 自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。
     * 如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 -1。如果存在一棵子树不平衡，则整个二叉树一定不平衡。
     *
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
        return getBalanceTreeDepth(root)>=0;

    }

    public int getBalanceTreeDepth(TreeNode node){
        if (node==null)
            return 0;
        int left = getBalanceTreeDepth(node.left);
        int right = getBalanceTreeDepth(node.right);

        if ( left==-1 || right==-1 || Math.abs(left-right) >1)
            return -1;
        return Math.max(left, right)+1;
    }


    // 官方题解的进一步优化，左子树不平衡了，没必要再计算右子树了。
    public int getBalanceTreeDepth2(TreeNode node){
        if (node==null)
            return 0;

        int left, right;
        if ( (left=getBalanceTreeDepth2(node.left) )==-1
                || ( right=getBalanceTreeDepth2(node.right)) ==-1
                || Math.abs(left-right) >1)
            return -1;
        return Math.max(left, right)+1;
    }

}
