package com.company.src.stackAndQueue;

import com.company.src.stackAndQueue.util.TreeNode;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 * 链接：https://leetcode.cn/problems/binary-tree-level-order-traversal/
 */
public class BTreeLevelOrderTraversal102 {

    /**
     * 利用队列层次遍历，每个队列元素保存treeNode和当前层级level。同层级的放在同一个list中输出。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>>  result = new ArrayList<>();
        Queue<LevelNode> queue =new LinkedList<>();

        if (root==null){
            return result;
        }

        queue.add(new LevelNode(0, root));
        int level = 0;
        while (!queue.isEmpty()){
            LevelNode levelNode = queue.peek();
            List<Integer> list = new ArrayList<>();
            while (levelNode.level == level){  // 同一层
                TreeNode node = levelNode.node;
                list.add(node.val);

                if (node.left !=null) {
                    queue.add(new LevelNode(levelNode.level+1, node.left));
                }
                if (node.right !=null) {
                    queue.add(new LevelNode(levelNode.level+1, node.right));
                }
                queue.poll();
                if (queue.isEmpty()){
                    break;
                }
                levelNode = queue.peek();

            }
            level++;
            result.add(list);

        }

        return result;

    }


    // 解法一换种写法: 用result的size表示层数。
    public static List<List<Integer>> levelOrder1(TreeNode root) {

        List<List<Integer>>  result = new ArrayList<>();
        Queue<LevelNode> queue =new LinkedList<>();

        if (root==null){
            return result;
        }

        queue.add(new LevelNode(0, root));
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()){
            LevelNode levelNode = queue.poll();
            TreeNode node = levelNode.node;

            if (levelNode.level == result.size()){
                result.add(new ArrayList<>());  //是时候开新的一层了
            }
            list = result.get(levelNode.level); //获取当前层的list，往里面添加值
            list.add(node.val);
            if (node.left !=null) {
                queue.add(new LevelNode(levelNode.level+1, node.left));
            }
            if (node.right !=null) {
                queue.add(new LevelNode(levelNode.level+1, node.right));
            }
        }
        return result;

    }


    public static class LevelNode{
        int level;
        TreeNode node;

        public LevelNode(int level, TreeNode node) {
            this.level = level;
            this.node = node;
        }
    }


    /**
     * 计数方式访问，用两个变量 保存此层尚未遍历的节点，和下一层待遍历的节点数
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>>  result = new ArrayList<>();
        Queue<TreeNode> queue =new LinkedList<>();

        if (root==null){
            return result;
        }

        TreeNode node = root;
        queue.add(node);
        int leftCount = 1, nextLevel=0;

        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            while (!queue.isEmpty() && leftCount!=0){
                node = queue.poll();
                leftCount--;
                list.add(node.val);
                if (node.left!=null){
                    queue.add(node.left);
                    nextLevel++;
                }
                if (node.right!=null){
                    queue.add(node.right);
                    nextLevel++;
                }
            }

            result.add(list);
            leftCount = nextLevel;
            nextLevel = 0;
        }
        return result;
    }


    /**
     * 解法三：类似解法二，利用每一层开始遍历时，queue.size就是这一层的元素个数，直接迭代这一层，再进入下一层。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>>  result = new ArrayList<>();
        if (root==null){
            return result;
        }

        Queue<TreeNode> queue =new LinkedList<>();

        TreeNode node = root;
        queue.add(node);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                list.add(node.val);
                if (node.left!=null)
                    queue.add(node.left);
                if (node.right!=null)
                    queue.add(node.right);
            }
            result.add(list);
        }
        return result;
    }


}
