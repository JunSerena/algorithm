package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 链接：https://leetcode.cn/problems/symmetric-tree/
 */
public class SymmetricTree101 {

    /**
     * 解法一：翻转一下左子树, 翻转后和右子树相同，就是对称的
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root==null)
            return true;
        TreeNode left = invertTree(root.left);
        return isSameTree(left, root.right);
    }


    public TreeNode invertTree(TreeNode root) {
        if (root==null)
            return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q==null){ //都为null
            return true;
        }else if (p==null || q==null){ //其中一个为null
            return false;
        }else if (p.val != q.val){
            return false;
        }else {
            return isSameTree(p.left, q.left)& isSameTree(p.right, q.right);
        }
    }


    /**
     * 解法二：双指针
     * 对称树的特点：它们的两个根结点具有相同的值，每个树的右子树都与另一个树的左子树镜像对称
     * 通过「同步移动」两个指针的方法来遍历这棵树，pp 指针和 qq 指针一开始都指向这棵树的根，随后 pp 右移时，qq 左移，pp 左移时，qq 右移。每次检查当前 pp 和 qq 节点的值是否相等，如果相等再判断左右子树是否对称。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root==null)
            return true;
        return check(root.left, root.right);

    }

    public boolean check(TreeNode p, TreeNode q) {

        if (p==null && q==null){ //都为null
            return true;
        }else if (p==null || q==null){ //其中一个为null
            return false;
        }else if (p.val != q.val){
            return false;
        }else {
            // p移动到左子树，q到右子树            p移动到右左子树，q到左子树
            return check(p.left, q.right)& check(p.right, q.left);
        }
    }

    /**
     * 解法三：迭代，用两个队列保存层次遍历节点。将两个结点的左右子结点按相反的顺序插入队列中
     */

}
