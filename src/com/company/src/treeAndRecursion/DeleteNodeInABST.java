package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

/**
 * 450. 删除二叉搜索树中的节点
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 * 示例 3:
 *
 * 输入: root = [], key = 0
 * 输出: []
 *
 * 链接：https://leetcode.cn/problems/delete-node-in-a-bst/
 */
public class DeleteNodeInABST {

    public static void main(String[] args) {
        int[] arr = new int[]{3,1,4,-1,2};
        TreeNode root = TreeNode.buildTree(arr);
        TreeNode node = deleteNode(root, 2);
        System.out.println(node.val);
    }


    /**
     * 解法一：先搜索，找到待删除节点的父亲节点。 再找到待删除节点的右子树中的最小节点(后继) 做为替代节点成为子树的新根。
     * @param root
     * @param key
     * @return
     */
    public static TreeNode deleteNode(TreeNode root, int key) {

        if (root==null)
            return null;
        TreeNode dummy =new TreeNode(10000000, root, null);
        // 找到待删节点的父节点
        TreeNode pre = findPredecessor(root, dummy, key);
        if (pre == null)
            return root;
        TreeNode del = pre.left;  //待删除节点
        boolean flag = true;
        if (pre.right!=null && pre.right.val==key){
            del = pre.right;
            flag = false;
        }

        TreeNode replace = null;

        // 当前del节点就是待删除节点。
        if (del.right !=null && del.left!=null){//左右子树都不为空

            //找到后继作为新的根
            TreeNode prevSuccessor = del, successor = del.right;

            while (successor.left!=null){
                prevSuccessor = successor;
                successor = successor.left;
            }
            // 如果后继节点的父节点不是待删除节点，那么后继节点一定他父亲节点的左边，后继节点的右子树将成为其父亲节点的左子树。
            if (prevSuccessor!=del){
                prevSuccessor.left= successor.right; //删除后继节点之前，先处理后继节点的子树，放到前驱节点的左子树上。
                successor.right = del.right;
            }
            successor.left = del.left;
            replace = successor;  // 后继节点作为替换节点，与原来的右子树建立联系。

        }else if (del.left==null){
            replace = del.right;
        }else {
            replace = del.left;
        }

        if (flag)
            pre.left = replace;
        else
            pre.right = replace;

        return dummy.left;

    }


    /**
     * 查找待删除节点的父亲节点
     * @param root
     * @param key
     * @return
     */
    public static TreeNode findPredecessor(TreeNode root, TreeNode prev, int key){
        if (root==null)
            return null;

        if (root.val==key)
            return prev;
        else if (root.val>key){
            return findPredecessor(root.left, root, key);
        }else {
            return findPredecessor(root.right, root, key);
        }
    }


    /**
     * 解法二：官方解法--递归
     * @param root
     * @param key
     * @return
     */
    public static TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            // 左右子树都存在，需要寻找后继节点
            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            root.right = deleteNode2(root.right, successor.val); // 先删除后继节点，再删除root节点，后继节点取代root节点。
            successor.right = root.right;
            successor.left = root.left;
            return successor;
        }
        return root;
    }


    // 手写一遍递归
    public static TreeNode deleteNode3(TreeNode root, int key) {
        if (root==null)
            return null;
        if (root.val > key){
            root.left = deleteNode3(root.left, key);
            return root;
        }
        if (root.val < key){
            root.right = deleteNode3(root.right, key);
            return root;
        }

        // root.val == key
        if (root.left == null && root.right==null)
            return null;
        else if (root.left==null){
            return root.right;
        }else if (root.right==null){
            return root.left;
        }else {
            //找后继
            TreeNode successor = root.right;
            while (successor.left!=null){
                successor = successor.left;
            }
            //删除后继, 并将后继者取代原有root
            root.right = deleteNode3(root.right, successor.val);
            successor.left = root.left;
            successor.right = root.right;
            return successor;
        }

    }

}
