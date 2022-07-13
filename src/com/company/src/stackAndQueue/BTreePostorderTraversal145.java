package com.company.src.stackAndQueue;

import com.company.src.stackAndQueue.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[3,2,1]
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
 * 树中节点的数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 链接：https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class BTreePostorderTraversal145 {

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root, list);
        return list;
    }

    public void postorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        postorderTraversal(node.left, list);
        postorderTraversal(node.right, list);
        list.add(node.val);
    }

    /**
     * 迭代后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
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
                stack.push(new Command("print", node));
                if (node.right!=null)
                    stack.push(new Command("go", node.right));
                if (node.left!=null)
                    stack.push(new Command("go", node.left));
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
     * 经典后序遍历迭代
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root)
            return list;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root, prev=null; //prev记录后序遍历时上一个遍历的节点
        while (!stack.isEmpty()|| node!=null){

            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();

            if (node.right!=null && node.right!=prev){ //当前节点的右节点不为空，并且没有遍历过，则需要遍历右子树
                stack.push(node);
                node = node.right;// 访问右子树

            }else {
                prev = node;  //记录当前 最后一个遍历的节点
                list.add(node.val);
                node = null; //遍历结束，node置空，不要再往右子树上去。
            }
        }

        return list;
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2, node3, null);
        TreeNode node1 = new TreeNode(1,null, node2);
        postorderTraversal3(node1);
    }


}
