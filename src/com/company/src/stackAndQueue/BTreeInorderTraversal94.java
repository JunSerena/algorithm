package com.company.src.stackAndQueue;

import com.company.src.stackAndQueue.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 链接：https://leetcode.cn/problems/binary-tree-inorder-traversal/
 */
public class BTreeInorderTraversal94 {
    /**
     * 递归调用实现二叉树的中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer > list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    public void preorder(TreeNode node, List<Integer> list) {
        if (null == node)
            return ;
        preorder(node.left, list);
        list.add(node.val);
        preorder(node.right, list);

    }

    /**
     * 解法2：利用栈，非递归实现二叉树的前序遍历
     * 模拟计算机的栈，中序 递归实现时，拆分成三步：先遍历左孩子，再打印，再遍历右孩子。要模拟栈的话，就反着来，先把右孩子压栈，再打印，再左孩子。
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer > list = new ArrayList<>();
        if (null == root)
            return list;

        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));

        while (!stack.isEmpty()){
            Command command = stack.pop();
            TreeNode node = command.node;
            if (command.s.equals("print")){
               list.add(node.val);
            }else {
               assert command.s.equals("go");
               if (node.right!=null){
                   stack.push(new Command("go",node.right));
               }
               stack.push(new Command("print", node));
               if (node.left!=null){
                   stack.push(new Command("go",node.left));
               }

            }
        }
        return list;
    }

    public static class Command{
        String s; // 命令类型（print、go）
        TreeNode node;

        public Command(String s, TreeNode node) {
            this.s = s;
            this.node = node;
        }
    }

    /**
     * 解法3：教科书经典解法 利用栈，非递归实现二叉树的中序遍历
     * 一直往左子树遍历，直到为空，打印当前节点值，再遍历右子树
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer > list = new ArrayList<>();
        if (null == root)
            return list;

        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;
        while (!stack.isEmpty() || node!=null){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        return list;
    }

}
