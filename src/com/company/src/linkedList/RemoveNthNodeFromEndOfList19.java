package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;
import com.sun.jmx.snmp.SnmpUnknownAccContrModelException;

import java.util.Stack;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndOfList19 {
    /**
     * 快慢指针。快指针先走n步，然后快慢指针同时走，快指针到达末尾，慢指针指向的就是目标节点的前一个节点。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy, fast = dummy;
        for (int i = 0; i < n && fast!=null ; i++) {
            fast = fast.next;
        }

        if (fast==null){
            return head;
        }
        while (fast.next !=null){
            slow = slow.next;
            fast = fast.next;
        }
        //fast到达末尾的时候，slow为倒数第n+1个节点。
        ListNode target  = slow.next;
        slow.next = target.next;
        return dummy.next;
    }

    //常用做法：先找链表长度，确定顺数位置，再执行删除操作
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        int len = 0;
        ListNode curr = head;
        while (curr!= null){
            len++;
            curr = curr.next;
        }
        if (len<n){
            return head;
        }

        int target = len - n; //正数第几个节点
        ListNode dummy = new ListNode(0, head);
        curr = dummy;
        for (int i = 0; i <target ; i++) {
            curr = curr.next;
        }

        curr.next = curr.next.next;
        return dummy.next;
    }

    // 解法三：利用栈，pop出第n个对象
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(0, head), curr = dummy;
        while (curr!=null){
            stack.push(curr);
            curr = curr.next;
        }

        ListNode target = null;
        for (int i = 0; i < n+1; i++) {
            if (stack.isEmpty()){
                return dummy.next;
            }
            target = stack.pop();
        }

        if (target!=null){
            target.next = target.next.next;
        }
        return dummy.next;
    }

}
