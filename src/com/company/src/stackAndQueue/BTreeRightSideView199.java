package com.company.src.stackAndQueue;

import com.company.src.stackAndQueue.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 *
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 *
 * 输入: []
 * 输出: []
 *
 * 链接：https://leetcode.cn/problems/binary-tree-right-side-view/
 */
public class BTreeRightSideView199 {
    // 二叉树的右视图，也就是需要返回每一层的最右边的节点。
    // 解法一：还是利用层次遍历，根据queue.size来决定每一层的最后一个节点
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root==null)
            return result;


        TreeNode node = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i <size-1 ; i++) {
                node = queue.poll();
                if (node.left!=null) queue.add(node.left);
                if (node.right!=null) queue.add(node.right);
            }
            //剩下每一层的最后一个节点
            node = queue.poll();
            result.add(node.val);
            if (node.left!=null) queue.add(node.left);
            if (node.right!=null) queue.add(node.right);

        }

        return  result;
    }
}
