package com.company.src.treeAndRecursion;

import com.company.src.linkedList.util.ListNode;
import com.company.src.stackAndQueue.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *
 * 链接：https://leetcode.cn/problems/path-sum-ii/
 */
public class PathSum2_113 {

    /**
     * 解法一：递归，用path保存当前路径，一旦当前路径和=sum，则加入到结果列表pathList中
     *
     * 写法类似 257号问题二叉树的所有路径 的官方解答
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> pathList = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        findPathSum(root, targetSum, path, pathList);
        return pathList;
    }

    public void findPathSum(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> pathList) {
        if (root==null)
            return;
        path.add(root.val);
        if (root.left==null && root.right==null && root.val==targetSum){
            pathList.add(new ArrayList<>(path)); //拷贝
        }

        findPathSum(root.left, targetSum-root.val, new ArrayList<>(path), pathList); //path需要拷贝
        findPathSum(root.right, targetSum-root.val, new ArrayList<>(path), pathList);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,4,8,11,-1,13,4,7,2,-1,-1,5,1};
        TreeNode root = TreeNode.buildTree(arr);
        List<List<Integer>> list =  pathSum2(root,22);
        System.out.println(list);
    }


    /**
     * 解法二：善用递归的返回值问题。
     *  类似257号题目求二叉树的所有路径的解法一。先取子树上的满足条件的路径list，再拼接当前节点。 类似自底向上
     * @param root
     * @param targetSum
     * @return
     */
    public static List<List<Integer>> pathSum2(TreeNode root, int targetSum) {

        List<List<Integer>> pathList = new ArrayList<>();
        if (root==null){
            return pathList;
        }
        if (root.left==null && root.right==null && root.val==targetSum){
            List<Integer> path = new ArrayList<>();
            path.add(root.val);
            pathList.add(path);
            return pathList;
        }
        List<List<Integer>> leftPath = pathSum2(root.left, targetSum-root.val);
        for (List<Integer> path: leftPath) {
            //插到前面
            path.add(0, root.val);
            pathList.add(path);
        }

        List<List<Integer>> rightPath = pathSum2(root.right, targetSum-root.val);
        for (List<Integer> path: rightPath) {
            //插到前面
            path.add(0, root.val);
            pathList.add(path); //一定要添加到结果list中
        }

        return pathList;
    }

}
