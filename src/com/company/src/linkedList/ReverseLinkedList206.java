package com.company.src.linkedList;

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

    public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
}
