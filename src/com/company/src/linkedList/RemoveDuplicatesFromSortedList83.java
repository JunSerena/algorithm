package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

/**
 *83. 删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * 示例 2：
 *
 *
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *
 * 链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
 */
public class RemoveDuplicatesFromSortedList83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head==null || head.next==null)
            return head;

        ListNode current = head, next = current.next;

        while (next!=null){
            //因为已经排序过了。所以直接遍历就好
            if (current.val == next.val){ //如果值相同，则删除下一个节点，连接到下下个节点，当前节点不变。
                current.next= next.next;
            }else {
                current = next; // 如果值不同，current和next指针都往下走
            }
            next = current.next;

        }
        return head;
    }

}
