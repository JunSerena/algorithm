package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

/**
 * 328. 奇偶链表
 * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 *
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 *
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 *
 * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。

 * 示例 1:
 *
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,3,5,2,4]
 * 示例 2:
 *
 * 输入: head = [2,1,3,5,6,4,7]
 * 输出: [2,3,6,7,1,5,4]
 */
public class OddEvenLinkedList328 {

    public static void main(String[] args) {
        // write your code here
        ListNode node5 = new ListNode(6);
        ListNode node4 = new ListNode(5, node5);
        ListNode node3 = new ListNode(4, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(2, node2);
        ListNode head = new ListNode(1, node1);

        oddEvenList(head);
    }


    /**
     * 用一个链表保存偶数索引位的节点，串接在奇数链表末尾
     * @param head
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode dummy = new ListNode(-1, head), prev = dummy, current = prev.next;
        ListNode evenHead = null, evenNode = null;

        for (int i = 1; current!=null; i++) {

            if ( i%2 == 0){ //偶数位置节点
                if (evenHead==null){
                    evenHead = current; //确定偶数链表头节点
                    evenNode = evenHead;
                }else {
                    evenNode.next = current;
                    evenNode =  evenNode.next;
                }

                //把偶数节点从原链表中摘除
                prev.next = current.next;

            }else {
                prev = current;
            }
            current = current.next;

        }

        // 两链表拼接
        if (evenHead!=null && evenHead==evenNode){
            evenHead.next = null;
        }
        if (evenNode!=null){
            evenNode.next = null;
        }

        prev.next = evenHead;

        return dummy.next;

    }


    /**
     * 同思路，更好的解法。
     * 以为奇偶就是固定的，可以用两个奇偶指针来同时遍历，做拼接
     * @param head
     * @return
     */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode odd = head;
        ListNode evenHead = odd.next, even = evenHead;

        while (even!=null && even.next != null){
            odd.next = even.next;  //奇数节点拼接
            odd = odd.next; //往下遍历

            even.next = odd.next;  // 偶数节点拼接
            even = even.next;
        }

        // 拼接奇偶链表
        odd.next = evenHead;
        return head;

    }






}
