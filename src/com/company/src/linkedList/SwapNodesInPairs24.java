package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 *
 * 链接：https://leetcode.cn/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPairs24 {

    // 三指针， 一对对翻转
    public ListNode swapPairs(ListNode head) {
        if (head==null|| head.next==null){
            return head;
        }

        ListNode dummy = new ListNode(-1, head), pre = dummy;
        ListNode one = head, two = head.next;

        while (one!=null && two!=null){
            // 翻转 pre one two变成 pre two one
            one.next = two.next;
            two.next = one;
            pre.next = two;

            // 更新
            pre = one;
            one = one.next;
            if (one!=null) two = one.next;
        }

        return dummy.next;

    }
}
