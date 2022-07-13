package com.company.src.stackAndQueue;

import com.company.src.stackAndQueue.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. 二叉树的前序遍历
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 链接：https://leetcode.cn/problems/binary-tree-preorder-traversal/
 */
public class BTreePreorderTraversal144 {
    /**
     * 递归调用实现二叉树的前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer > list = new ArrayList<>();
        if (null == root)
            return list;

        preorder(root, list);
        return list;
    }

    public void preorder(TreeNode node, List<Integer> list) {
        if (null == node)
            return ;
        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);

    }

    /**
     * 解法2：利用栈，非递归实现二叉树的前序遍历
     * 比较好理解：模拟计算机的栈，前序 递归实现时，拆分成三步：先打印，再遍历左孩子，再遍历右孩子。要模拟栈的话，就反着来，先把右孩子压栈，再左孩子，再打印。
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer > list = new ArrayList<>();
        if (null == root)
            return list;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (null == node)
                continue;

            stack.push(node.right); // 右孩子压栈
            stack.push(node.left); // 左孩子压栈 这样会先取出左孩子
            list.add(node.val); //前序遍历 先获取元素值

        }
        return list;
    }

    /**
     * 解法3：利用栈，非递归实现二叉树的前序遍历
     * 此解法完全可以适用于中序和后序。主要是将node的入栈分为两类，一类是打印，一类是go访问
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer > list = new ArrayList<>();
        if (null == root)
            return list;

        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go",root)); //访问root
        while (!stack.isEmpty()){
            Command command = stack.pop();
            TreeNode node = command.node;

            if (command.s.equals("print")){
                list.add(node.val);
            }else {
                assert command.s.equals("go");
                if (null!= node.right){
                    stack.push(new Command("go", node.right)); // 右孩子压栈
                }
                if (null!= node.left){
                    stack.push(new Command("go", node.left)); // 左孩子压栈
                }
                stack.push(new Command("print",node));   //这个位置 是前序遍历，放最前面是后序遍历，放中间是中序遍历
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
     * 解法4：教科书经典解法 利用栈，非递归实现二叉树的前序遍历
     * 一直往左子树遍历，直到为空，再遍历右子树
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal4(TreeNode root) {
        List<Integer > list = new ArrayList<>();
        if (null == root)
            return list;

        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;

        while (!stack.isEmpty() || node!=null){

            while (node!=null){
                list.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }

        return list;
    }


    /**
     * 解法5：非递归遍历，treeNode绑定一个count计数。前序遍历时，count=1就输出，中序为2输出，后序为3时输出。
     */

}
