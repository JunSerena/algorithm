package com.company.src.linkedList;


import com.company.src.linkedList.util.ListNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 *
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 *
 * 链接：https://leetcode.cn/problems/partition-list/
 */
public class PartitionList86 {

    public static void main(String[] args) {
        // write your code here
        ListNode node5 = new ListNode(2);
        ListNode node4 = new ListNode(5, node5);
        ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(4, node2);
        ListNode head = new ListNode(1, node1);

        partition2(head, 3);
    }

    /**
     * 解法一：用栈，把大于等于的都压栈，然后弹出来，链成链表，再衔接到前面小于X的元素上。
     * 用队列应该也可以
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1000, head), prev = dummy, current=prev.next;
        Stack<ListNode> stack = new Stack<>();

        while (current!=null){
            if (current.val>=x){  // prev不改，current压栈，current往下走
                stack.push(current);
                prev.next = current.next;

            }else { // prev、current都往下走
                prev = current;
            }
            current = current.next;
        } // 遍历结束，prev就是最后一个比x小的节点

        //拿到链表尾部
        if (!stack.isEmpty()){
            current = stack.pop();
            current.next = null;
        }

        while (!stack.isEmpty()){
             stack.peek().next = current;
             current = stack.pop();
        }

        //衔接链表
        prev.next = current;

        return dummy.next;
    }


    // 用队列
    public static ListNode partition2(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1000, head), prev = dummy, current=prev.next;
        Queue<ListNode> queue = new LinkedList<>();

        while (current!=null){
            if (current.val>=x){  // prev不改，current压栈，current往下走
                queue.add(current);
                prev.next = current.next;

            }else { // prev、current都往下走
                prev = current;
            }
            current = current.next;
        } // 遍历结束，prev就是最后一个比x小的节点

        //读队列
        current = prev;
        while (!queue.isEmpty()){
            current.next = queue.poll();
            current = current.next;
        }
        current.next = null;

        return dummy.next;

    }

    /**
     * 不用栈和队列，直接保存第一个比x大的节点
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition3(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1000, head), prev = dummy, current=prev.next;
        ListNode firstLarge = null, largeNode = null;
        while (current!=null){

            if (current.val>=x){  // prev不改，current节点链接到large链表上去，current往下走
                if (firstLarge==null){
                    firstLarge = current;
                    largeNode = firstLarge;
                }else {
                    largeNode.next = current;
                    largeNode = largeNode.next;
                }
                prev.next = current.next;

            }else { // prev、current都往下走
                prev = current;
            }
            current = current.next;
        } // 遍历结束，prev就是最后一个比x小的节点


        // 如果只有一个最大节点，要将其next置空
        if (largeNode!=null &&  largeNode == firstLarge){
            largeNode.next = null;
        }

        // 大节点末尾要置空
        if (largeNode != null){
            largeNode.next = null;
        }

        prev.next = firstLarge;

        return dummy.next;

    }


}
