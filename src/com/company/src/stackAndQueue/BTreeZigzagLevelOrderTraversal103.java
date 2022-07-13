package com.company.src.stackAndQueue;

import com.company.src.stackAndQueue.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 * 链接：https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
 */
public class BTreeZigzagLevelOrderTraversal103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root==null)
            return result;

        //之字型遍历。根据层级来判断是正向还是反向（反向的话，插入list头部）。层级用result的size来确定
        TreeNode node = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            int size = queue.size();
            int level = result.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i <size ; i++) {
                node = queue.poll();
                if (level%2==0){
                    list.add(node.val);
                }else {
                    list.add(0, node.val);
                }
                if (node.left!=null) queue.add(node.left);
                if (node.right!=null) queue.add(node.right);
            }

            result.add(list);
        }

        return result;

    }
}
