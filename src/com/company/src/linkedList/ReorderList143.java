package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;
import com.sun.tools.internal.ws.wsdl.framework.WSDLLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 * 示例 2：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 *
 * 链接：https://leetcode.cn/problems/reorder-list/
 */
public class ReorderList143 {
    public static void main(String[] args) {
//        ListNode node5 = new ListNode(6);
//        ListNode node4 = new ListNode(0, node5);
//        ListNode node3 = new ListNode(4, node4);
        ListNode node2 = new ListNode(3);
        ListNode node1 = new ListNode(5, node2);
        ListNode head = new ListNode(-1, node1);

        reorderList2(head);
    }
    /**
     * 解法一：先二分链表，再逆转右边链表，之后两链表一一合并
     * @param head
     */
    public static void reorderList(ListNode head) {
        if (head==null || head.next ==null){
            return;
        }

        //找到中间节点
        ListNode dummy = new ListNode(0,head), slow = dummy, fast = dummy;
        while (fast.next!=null){
            slow = slow.next;
            fast = fast.next;
            if (fast.next!=null){
                fast = fast.next;
            }
        }

        // head 到 slow是 左链表(包含slow)，slow到尾巴上为右边链表(不包含slow)
        ListNode rightHead = slow.next;
        //左边链表断开链接
        slow.next = null;

        //翻转右链表
        ListNode dummyRight = new ListNode(0, rightHead), curr = dummyRight.next;
        while (curr.next!=null){
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = dummyRight.next;
            dummyRight.next = next;
        }

        // 两两节点合并
        ListNode left = dummy.next, right = dummyRight.next;
        while (left!=null && right !=null){
            ListNode rightNext = right.next;
            ListNode leftNext = left.next;
            right.next = left.next;
            left.next = right;
            left = leftNext;
            right = rightNext;
        }
        System.out.println("www");
    }

    /**
     * 解法2：利用arrayList来根据索引获取node
     * @param head
     */
    public static void reorderList2(ListNode head) {
        if (head==null || head.next ==null){
            return;
        }

        ListNode curr = head;
        List<ListNode> list = new ArrayList<>();
        while (curr!=null){
            list.add(curr);
            curr = curr.next;
        }
        int len = list.size();
        int i=0, j=len-1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {  //双数节点
                break;
            }
            list.get(j).next = list.get(i); //单数节点，j--以后就会i==j了
            j--;
        }
        list.get(i).next = null;
    }
}
