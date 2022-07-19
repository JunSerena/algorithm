package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1：
 *
 * 输入：root = [2,1,3]
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *
 * 链接：https://leetcode.cn/problems/validate-binary-search-tree
 */
public class ValidateBinarySearchTree98 {

    public static void main(String[] args) {
        int[] arr = new int[]{5,4,6,-1,-1,3,7};
        System.out.println(isValidBST5(TreeNode.buildTree(arr)));
    }


    /**
     * 解法一：当前节点的值会比左子树中的最大值还要大，比右子树中的最小值还要小。
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        if (root==null)
            return true;
        if (root.right==null && root.left == null)
            return true;
        if (!isValidBST(root.left))
            return false;
        if (!isValidBST(root.right))
            return false;
        double max = getMaxOfTree(root.left);  //左子树中的最大值
        double min = getMinOfTree(root.right); // 右子树中的最小值

        return root.val> max && root.val<min;

    }

    // 两个函数，获取右子树最小值 和获取左子树最大值
    public static double getMinOfTree (TreeNode node){
        double min = Double.MAX_VALUE;
        if (node==null)
            return min;
        while (node.left!=null){
            node=node.left;
        }

        return node.val;
    }

    public static double getMaxOfTree (TreeNode node){
        double max = -Double.MAX_VALUE;  //Double.MIN_VALUE是个正数。。。表示64位双精度值能表示的最小正数。
        if (node==null)
            return max;
        while (node.right!=null){
            node=node.right;
        }
        return node.val;
    }


    /**
     * 解法二：中序遍历，是递增序列，就是二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i)>=list.get(i+1)){
                return false;
            }
        }
        return true;
    }

    public void inorderTraversal(TreeNode node, List<Integer> list){
        if (node==null)
            return;
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }


    /**
     * 优化解法二：不需要完整遍历，只要发现当前节点比list中最后一个小或者等于，就return
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return inorder(root, list);
    }

    public boolean inorder(TreeNode node, List<Integer> list){
        if(node==null){
            return true;
        }

        if (!inorder(node.left, list)){  // 注意：递归为false必须要返回。不能再往后面执行。否则会覆盖false的值。
            return false;
        }

        int size = list.size();
        if (size>0 && list.get(size-1)>=node.val){
            return false;
        }
        list.add(node.val);

        return inorder(node.right, list); // 注意：递归为false必须要返回。不能再往后面执行。否则会覆盖false的值。

    }


    /**
     * 解法三再换个写法 会比解法三看起来好看一点
     * @param root
     * @return
     */
    double last = Integer.MIN_VALUE - 1.0;
    public boolean isValidBST4(TreeNode root) {
        if (root==null)
            return true;
        if (isValidBST4(root.left)){
            if (last<root.val){
                last = root.val;
                return isValidBST4(root.right);
            }
            return false;
        }
        return false;

    }


    /**
     * 官方解答：思路
     *
     * 如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值； 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
     * 它的左右子树也为二叉搜索树。
     *
     */
    public static boolean isValidBST5(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean dfs(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }

        // 左子树不断更新上界upper（上界在缩小）。右子树不断更新下界lower（下界在变大）
        return dfs(node.left, lower, node.val) && dfs(node.right, node.val, upper);
    }


}

