package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

/**
 *129. 求根节点到叶节点数字之和
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * 示例 2：
 *
 *
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 *
 * 链接：https://leetcode.cn/problems/sum-root-to-leaf-numbers/
 */
public class SumRootToLeafNumbers129 {

    public static void main(String[] args) {

    }


    public int sumNumbers(TreeNode root) {
        return getSum(root, 0);
    }

    public int getSum(TreeNode root, int prevValue) {
        int sum = 0;
        if (root==null){
            return sum;
        }
        if (root.left==null && root.right==null ){
            sum += prevValue*10+root.val;
            return sum;
        }

        // 关键递归点
        // 当前路径的sum = 上一个节点路径代表的值*10+当前节点
        sum += getSum(root.left, prevValue*10+root.val);
        sum += getSum(root.right, prevValue*10+root.val);

        return sum;

    }

    // 还有其他思路，也可以先获取所有的到叶子节点的路径，最后遍历路径列表，相加。不写了，可参照257号问题求所有到叶子节点的路径。
}
