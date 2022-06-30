package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

import java.util.Stack;

/**
 * 206 反转链表：给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 思路：三个指针
 */
public class ReverseLinkedList206 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static void main(String[] args) {
	// write your code here
    }
    public ListNode reverseList(ListNode head) {
        //1、如果链表为空
        if (null == head || null == head.next)
            return head;
        ListNode prev = head;
        ListNode current = head.next;
        ListNode next = current.next;
        while (next != null){  //next为空，说明current已经是最后一个节点
            //将链表的指针反转
            current.next = prev;
            //下一步
            prev = current;
            current = next;
            next = next.next;
        }
        current.next = prev;
        //处理原链表头，即反转后的链表的尾巴
        head.next = null;
        return current;
    }

    //换种写法
    public ListNode reverseList2(ListNode head) {
        //1、如果链表为空
        if (null == head )
            return null;
        ListNode prev = head;
        ListNode current = head.next;

        while (current != null){
            ListNode next = current.next;
            //将链表的指针反转
            current.next = prev;
            //下一步
            prev = current;
            current = next;
        }
        //current为空，说明current已经是最后一个节点的指针节点，prev是最后一个指针，说明链表反转已经完成
        //处理原链表头，即反转后的链表的尾巴
        head.next = null;
        return prev;
    }


    /**
     * 核心思想：当前节点指针current和next，next反转指向current。备份原来next的下一个节点。往后遍历
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {

        if (null == head || head.next==null)
            return head;

        ListNode current = head;
        ListNode next=current.next, backup;

        while ( next != null){
            //next = current.next;
            backup = next.next;  // 备份反转的那根引线所指向原节点，否则断开链接后会找不到

            next.next = current; // 反转的关键步骤

            current = next;
            next = backup;
        }

        head.next = null;
        return current;

    }

    /**
     * 栈的写法也可以，先压栈，再弹出
     * @param head
     * @return
     */
    public ListNode reverseList4(ListNode head) {
        if (null == head || head.next==null)
            return head;
        Stack<ListNode> stack = new Stack<>();
        ListNode current = head;
        ListNode next=current.next;

        // 先入栈
        while (current!=null){
            stack.push(current);
            current=current.next;
        }

        //先拿头节点
        ListNode newHead = stack.pop();
        current = newHead;

        //再出栈
        while (!stack.isEmpty()){
            ListNode top = stack.pop();
            current.next = top;
            current = top;
        }
        current.next = null; //注意最后一个节点需要指向null,否则会成环

        return newHead;
    }



}
