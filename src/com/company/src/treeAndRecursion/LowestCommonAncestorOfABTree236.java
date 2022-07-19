package com.company.src.treeAndRecursion;

import com.company.src.Test;
import com.company.src.stackAndQueue.util.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 *
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 *
 * 链接：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestorOfABTree236 {


    /**
     * 方法一：递归 判断p/q是否分别在左右子树上，或者p/q就是当前节点且左右子树中包含另一个节点。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null)
            return null;
        if (root.val==p.val || root.val==q.val)
            return root;


        if ( containNode(root.left, p)&&containNode(root.left, q))
            return lowestCommonAncestor(root.left, p, q);
        else if (containNode(root.right, p)&&containNode(root.right, q))
            return lowestCommonAncestor(root.right, p, q);
        else
            return root;
    }

    public static boolean containNode(TreeNode root, TreeNode p){
        if (root==null || p==null){
            return false;
        }

        if (root.val==p.val)
            return true;
        return containNode(root.left,p) || containNode(root.right,p);
    }

    /**
     * 解法二：官方解答，递归实现。
     * f_lson表示左子树包含p或者q，f_rson表示右子树包含p或者q。
     * 最近祖先肯定需要满足条件：
     * （f_lson&&f_rson) || ( (p==x||q==x)&& (f_lson||f_rson) )
     * 即，要么pq分布在左右子树上，要么p、q一个为另一个的祖先节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return ans;
    }

    private TreeNode ans;
    // 递归函数，判断当前root是否包含p或者q
    public boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if (root==null)
            return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);

        if ((lson&&rson) || ((root.val==p.val||root.val==q.val)&& (lson||rson))){
            ans=root;
        }
        return lson||rson || (root.val==p.val||root.val==q.val);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,5,1,6,2,0,8,-1,-1,7,4};
        lowestCommonAncestor3(TreeNode.buildTree(arr), new TreeNode(5), new TreeNode(4));
    }

    /**
     * 解法三：记录父亲节点。然后回溯链表，找到最近公共节点。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        findParent(root, null);
        while (p!=null){
            set.add(p.val);
            p = hashMap.get(p.val);
        }

        while (!set.contains(q.val)){
            q = hashMap.get(q.val);
        }
        return q;
    }

    private static HashMap<Integer, TreeNode> hashMap=new HashMap<>();
    private static Set<Integer> set=new HashSet<>();

    public static void findParent(TreeNode node, TreeNode parent){
        if (node==null){
            return;
        }
        hashMap.put(node.val,parent);
        findParent(node.left, node);
        findParent(node.right, node);

    }
}
