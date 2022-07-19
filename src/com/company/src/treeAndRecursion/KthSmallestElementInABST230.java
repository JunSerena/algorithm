package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

import java.util.Stack;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 *
 *
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 *
 * 链接：https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementInABST230 {
    public static void main(String[] args) {
        int[] arr= new int[]{5,3,6,2,4,-1,-1,1};
        System.out.println(kthSmallest3(TreeNode.buildTree(arr), 3,new int[]{0}));

        Integer a = new Integer(12);
        Integer b = new Integer(12);
        System.out.println(a==b);

        Integer c = 1200;
        Integer d = 1200;
        System.out.println(c==d);

    }
    /**
     * 解法一：中序遍历获得前k序列。迭代
     * @param root
     * @param k
     * @return
     */
    public static int kthSmallest(TreeNode root, int k) {
        if (root == null)
            return -1;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int count=0;

        while (!stack.isEmpty() || node!=null){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            count++;
            if (count==k)
                return node.val;

            node = node.right;
        }

        return -1;
    }

    /**
     * 递归
     * @param root
     * @param k
     * @return
     */
    private static int count = 0;
    public static int kthSmallest2(TreeNode root, int k) {
        if (root==null)
            return -1;
        int res =kthSmallest2(root.left,k);
        if ( res != -1){
            return res;
        }
        count++;
        if (count==k)
            return root.val;
        else
            return kthSmallest2(root.right, k);

    }

    // 如果传int count，就会出错，因为count为基础数据类型。
    public static int kthSmallest3(TreeNode root, int k, int[] count) {
        if (root==null)
            return -1;
        int res =kthSmallest3(root.left,k,count);
        if ( res != -1){
            return res;
        }
        count[0]++;
        if (count[0]==k)
            return root.val;
        else
            return kthSmallest3(root.right, k,count);

    }
}
