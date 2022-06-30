package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

/**
 * 147. 对链表进行插入排序
 * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
 *
 * 插入排序 算法的步骤:
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
 *
 * 对链表进行插入排序。
 *
 *
 *
 * 示例 1：
 *
 * 输入: head = [4,2,1,3]
 * 输出: [1,2,3,4]
 *
 * 示例 2：
 *
 * 输入: head = [-1,5,3,4,0]
 * 输出: [-1,0,3,4,5]
 *
 *
 * 提示：
 *
 * 列表中的节点数在 [1, 5000]范围内
 * -5000 <= Node.val <= 5000
 *
 * 链接：https://leetcode.cn/problems/insertion-sort-list/
 */
public class InsertionSortList147 {


    /**
     * 解法一：升序链表。从head到pre都是升序，对current从头开始找它对应的位置。找到以后插入
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head==null || head.next==null){
            return head;
        }
        ListNode dummy = new ListNode(-100000, head), preIt, iterator, pre = head, current = pre.next;

        while (current!=null){
            ListNode next = current.next;
            preIt = dummy;
            iterator = preIt.next;
            while (current.val>=iterator.val && current!=iterator){
                preIt = iterator;
                iterator = iterator.next;
            }
            if (current == iterator){  //不需要往前面插入
                pre = current;
            }else {
                //注意点：需要把current摘出来,pre链接到next。否则会成环，然后丢掉后面的next
                pre.next = next;

                //插入iterator前
                current.next = iterator;
                preIt.next = current;
            }
            current = next;
        }
        return dummy.next;
    }


    /**
     * 解法一的优化，先判断current和pre的值大小，如果比pre的值要大于等于，说明current就在当前位置上，不用去前面找。
     * @param head
     * @return
     */
    public ListNode insertionSortList2(ListNode head) {
        if (head==null || head.next==null){
            return head;
        }
        ListNode dummy = new ListNode(-100000, head), preIt, iterator, pre = head, current = pre.next;

        while (current!=null){
            ListNode next = current.next;

            //如果current的值不比pre小，说明current的位置不用改，直接遍历到下一个
            if (current.val>=pre.val){
                pre = current;
                current = next;
                continue;
            }

            preIt = dummy;
            iterator = preIt.next;
            while (current.val>=iterator.val && current!=iterator){
                preIt = iterator;
                iterator = iterator.next;
            }
            //把current摘出来,pre链接到next
            pre.next = next;

            //插入iterator前
            current.next = iterator;
            preIt.next = current;

            current = next;
        }
        return dummy.next;
    }
}
