package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 112. 路径总和
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 解释：等于目标和的根节点到叶节点路径如上图所示。
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * 解释：树中存在两条根节点到叶子节点的路径：
 * (1 --> 2): 和为 3
 * (1 --> 3): 和为 4
 * 不存在 sum = 5 的根节点到叶子节点的路径。
 * 示例 3：
 *
 * 输入：root = [], targetSum = 0
 * 输出：false
 * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
 *
 * 链接：https://leetcode.cn/problems/path-sum/
 */
public class PathSum112 {
    //递归，转化为在左子树和右子树上找
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null)
            return false;
        if (root.left==null && root.right==null )
            return root.val==targetSum;
        return hasPathSum(root.left, targetSum-root.val) || hasPathSum(root.right, targetSum-root.val);
    }


    public static void main(String[] args) {
        int[] arr = new int[]{ 1,2,-1,3,-1,4,-1,5};
        TreeNode root = TreeNode.buildTree(arr);
        hasPathSum2(root,15);
    }



    /**
     * 解法二：广度优先搜索。两个队列，一个保存遍历的节点，一个保存对应想要找的剩余的目标和
     */
    public static boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();

        nodeQueue.add(root);
        sumQueue.add(targetSum);

        while (!nodeQueue.isEmpty()){
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                int sum = sumQueue.poll();
                if (node.left==null && node.right==null && node.val==sum){
                    return true;
                }
                if (node.left!=null){
                    nodeQueue.add(node.left);
                    sumQueue.add(sum-node.val);
                }
                if (node.right!=null){
                    nodeQueue.add(node.right);
                    sumQueue.add(sum-node.val);
                }

            }
        }
        return false;
    }

}
