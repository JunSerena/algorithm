package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * 示例 2：
 *
 *
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 *
 * 链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
 */
public class RemoveDuplicatesFromSortedList2_82 {
    public static void main(String[] args) {
        ListNode node6 = new ListNode(5);
        ListNode node5 = new ListNode(4, node6);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(2, node2);
        ListNode head = new ListNode(1, node1);

        deleteDuplicates(head);
    }
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1,head);

        ListNode pre = dummy, current = dummy.next;
        boolean delCurrent = false; // 如果是重复元素，需要先清除next的节点，最后清除current节点
        while (current!=null ){
            ListNode next = current.next;
            if (next!=null && current.val == next.val){
                //先清掉后面的重复元素
                current.next = next.next;
                delCurrent = true;

            }else {
                if (delCurrent){  //需要清除当前current节点，pre节点不动
                    pre.next = current.next;
                    delCurrent = false;
                }else {           // 不需要清除当前current节点，pre节点和current节点都往下移动
                    pre = pre.next;
                }
                current = pre.next;
            }
        }

        return dummy.next;
    }
}
