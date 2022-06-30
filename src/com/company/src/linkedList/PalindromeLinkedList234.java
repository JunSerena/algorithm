package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：head = [1,2]
 * 输出：false
 *
 * 链接：https://leetcode.cn/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList234 {
    //方法一：将值复制到数组中，再双指针比较是否回文
    public boolean isPalindrome(ListNode head) {
        if (head==null || head.next==null){
            return true;
        }
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node!=null){
            list.add(node.val);
            node = node.next;
        }

        int i=0, j =list.size()-1;
        while (i<j){
            if (!list.get(i).equals(list.get(j))){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // 方法二：找到链表中间节点，翻转后面半部分，然后遍历。（最好将其复原）
    public static boolean isPalindrome2(ListNode head) {
        if (head==null || head.next==null){
            return true;
        }
        ListNode dummy = new ListNode(0,head);
        ListNode slow = dummy, fast = dummy;
        // 前半部分为[head,slow]，后半部分为[slow.next， tail]
        while (fast.next!=null){
            slow  = slow.next;
            fast = fast.next;
            if (fast.next!=null){
                fast = fast.next;
            }
        }

        //以slow为dummy节点，翻转后半部分，固定dummy和curr节点 翻转next节点
        ListNode curr = slow.next;
        while (curr.next!=null){
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = slow.next;
            slow.next = next;
        }

        // 翻转结束，比较[head,slow]和[slow.next，tail)区别
        ListNode left = head, right = slow.next;
        while (left!=slow.next && right!=null){
            if (left.val!=right.val)
                return false;
            left = left.next;
            right = right.next;
        }

        //以slow为dummy节点，再次翻转后半部分，复原链表(不做也没影响)
        curr = slow.next;
        while (curr.next!=null){
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = slow.next;
            slow.next = next;
        }

        return true;

    }

    public static void main(String[] args) {
//        ListNode node3 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node1 = new ListNode(2, node2);
        ListNode head = new ListNode(1, node1);

        isPalindrome2(head);
    }
}
