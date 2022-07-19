package com.company.src.treeAndRecursion;

import com.company.src.linkedList.util.ListNode;
import com.company.src.stackAndQueue.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：["1"]
 *
 * 链接：https://leetcode.cn/problems/binary-tree-paths/
 */
public class BinaryTreePaths257 {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,-1,5};
        TreeNode root = TreeNode.buildTree(arr);
        List<String> list = binaryTreePaths(root);
        System.out.println(list);
    }

    /**
     * 解法一：善用递归的返回值问题。
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root==null)
            return result;
        if (root.left==null && root.right == null){
            result.add(root.val+"");
            return result;
        }

        List<String> leftList = binaryTreePaths(root.left);
        for (String value : leftList) {
            result.add(root.val + "->" + value);
        }

        List<String> rightList = binaryTreePaths(root.right);
        for (String s : rightList) {
            result.add(root.val + "->" + s);
        }
        return result;


    }


    /**
     * 官方解答：善用递归传入的参数。需要注意的是，参数一旦传入，如果是引用类型变量，在递归中更改，出来也会更新。
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        constructPaths(root, "", paths);
        return paths;
    }

    public void constructPaths(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            StringBuffer pathSB = new StringBuffer(path);
            pathSB.append(Integer.toString(root.val));
            if (root.left == null && root.right == null) {  // 当前节点是叶子节点
                paths.add(pathSB.toString());  // 把路径加入到答案中
            } else {
                pathSB.append("->");  // 当前节点不是叶子节点，继续递归遍历
                constructPaths(root.left, pathSB.toString(), paths);
                constructPaths(root.right, pathSB.toString(), paths);
            }
        }
    }

}
/**
 * 官方解答：思考方向不同，官方是先拼接，再递归（有点自顶向下的意思）。解法1是先递归，再拼接，自底向上。
 * @param root
 * @return
 */