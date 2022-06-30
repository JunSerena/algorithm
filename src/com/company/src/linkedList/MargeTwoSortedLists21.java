package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 * 链接：https://leetcode.cn/problems/merge-two-sorted-lists/
 */
public class MargeTwoSortedLists21 {
    public static void main(String[] args) {

        ListNode node5 = new ListNode(4);
        ListNode node4 = new ListNode(2, node5);
        ListNode node3 = new ListNode(1, node4);

        ListNode node2 = new ListNode(4);
        ListNode node1 = new ListNode(3, node2);
        ListNode head = new ListNode(1, node1);

        mergeTwoLists(node3, head);

    }


    /**
     * 以list1为基准，往list1里面插入节点。
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        else if (list2 == null)
            return list1;

        ListNode p = list1, q = list2;
        ListNode dummy = new ListNode(0, list1);  // dummynode 先挂在list1前面， p=dummy.next
        ListNode pre = dummy;
        while (p!=null && q != null){

            if (p.val>q.val){  //把q节点插入到p节点前
                pre.next = q;
                q = q.next;
                pre.next.next = p;
            }else {
                //pre.next = p;
                p = p.next;
            }
            pre = pre.next;
        }

        if (p == null){
            pre.next = q;
        }

        return dummy.next;
    }


    // 解法二，不以某个列表为基准，直接往dummy node后面插入元素
    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        else if (list2 == null)
            return list1;

        ListNode p = list1, q = list2;
        ListNode dummy = new ListNode(0), pre = dummy;
        while (p!=null && q != null){

            if (p.val>q.val){  //把q节点插入到p节点前
                pre.next = q;
                q = q.next;
            }else {
                pre.next = p;
                p = p.next;
            }
            pre = pre.next;
        }

        // p和q肯定至少有一个为null
        if (p == null){
            pre.next = q;
        }else {
            pre.next = p;
        }

        return dummy.next;
    }


}
