package com.company.src.stackAndQueue.util;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

    public int val;
    public  TreeNode left;
    public  TreeNode right;
    public  TreeNode() {}
    public  TreeNode(int val) { this.val = val; }
    public  TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
    }

    // 层次遍历构建二叉树
    public static TreeNode buildTree(int[] arr){  //-1表示null节点
        //将arr封装成TreeNode
        int len = arr.length;
        TreeNode[] list = new TreeNode[len];
        for (int i = 0; i <len ; i++) {
            if (arr[i]== -1){
                list[i]=null;
            }else
                list[i]= new TreeNode(arr[i]);
        }

        // 层次遍历 i为父亲节点，j为孩子节点
        for (int i = 0, j=1; j <len; i++, j=j+2) {
            TreeNode node = list[i];
            if (node!=null){
                node.left = list[j];
                if (j+1!=len)
                    node.right = list[j+1];
            }else {
                j=j-2;
            }
        }
        return list[0];
    }
}
