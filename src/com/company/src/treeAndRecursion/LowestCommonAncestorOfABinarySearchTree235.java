package com.company.src.treeAndRecursion;

import com.company.src.stackAndQueue.util.TreeNode;

/**
 * 235. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 链接：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestorOfABinarySearchTree235 {

    public static void main(String[] args) {
        int[] arr = new int[]{6,2,8,0,4,7,9,-1,-1,3,5};
        TreeNode root = TreeNode.buildTree(arr);
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);
        TreeNode node = lowestCommonAncestor(root, p, q);
    }

    /**
     * 解法一：可以通过。但是忽略了条件，二叉搜索树。解法一可以适用于任何二叉树。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 以某个节点为根，查找是否包含p和q。
        if (root == null)
            return null;

        // 判断标准，当前节点的子树包含p和q,但是左子树不包含，右子树也不包含。就可以直接return
        if (containNode(root, p) && containNode(root, q)){
            boolean leftContain = containNode(root.left,p) &&  containNode(root.left,q) ;
            boolean rightContain = containNode(root.right, p) &&  containNode(root.right,q) ;

            if (leftContain){
                return lowestCommonAncestor(root.left, p, q);
            }else if (rightContain){
                return lowestCommonAncestor(root.right, p, q);
            }else {
                return root;
            }

        }
        return null;

    }

    public static boolean containNode(TreeNode root, TreeNode p){
        if (root==null){
            return false;
        }
        if (root.val == p.val){
            return true;
        }
        return containNode(root.left, p) || containNode(root.right, p);
    }


    /**
     * 解法二：优化，利用二叉搜索树 当前节点大于左孩子，小于右孩子的特点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null)
            return null;
        if (root.val==p.val || root.val==q.val )
            return root;
        if ((root.val>p.val && root.val<q.val) || (root.val>q.val && root.val<p.val))
            return root;
        if (root.val<p.val && root.val<q.val){
            return lowestCommonAncestor(root.right, p, q);
        }

        return lowestCommonAncestor(root.left, p, q);

    }

    // 优化解法二，减少if语句
    public TreeNode lowestCommonAncestor2_2(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null)
            return null;

        if (root.val<p.val && root.val<q.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        if (root.val>p.val && root.val>q.val){
            return lowestCommonAncestor(root.left, p, q);
        }

        return root;

    }

}
