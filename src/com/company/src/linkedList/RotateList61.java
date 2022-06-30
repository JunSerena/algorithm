package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 *
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 * 链接：https://leetcode.cn/problems/rotate-list/
 */
public class RotateList61 {

    public static void main(String[] args) {
        ListNode node5 = new ListNode(6);
        ListNode node4 = new ListNode(5);
        ListNode node3 = new ListNode(4, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(2, node2);
        ListNode head = new ListNode(1, node1);

        rotateRight(head,2);
    }

    //闭合为环，找到对应节点，断开链接
    public static ListNode rotateRight(ListNode head, int k) {
        if (head==null || head.next == null){
            return head;
        }
        //先获取链表总长度
        int len = 1;
        ListNode tail = head;
        while (tail.next!=null){
            len++;
            tail = tail.next;
        }//找到原链表尾节点

        if (k%len==0){
            return head;
        }
        int target = len-k%len;
        ListNode cur = head;
        for (int i = 1; i <target ; i++) {
            cur = cur.next;
        }

        //找到新的尾节点和头节点，链接原尾节点到原头节点
        ListNode result = cur.next;
        cur.next = null;
        tail.next = head;
        return result;

    }


}
