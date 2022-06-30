package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

import java.util.List;
import java.util.Stack;

/**
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 *
 *
 * 示例1：
 *
 *
 *
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * 示例2：
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 * 示例3：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 */
public class AddTwoNumbers2_445 {
    //解法一：用栈
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>(); //栈里不用存listNode，直接存值就好，减少空间占用
        Stack<Integer> stack2 = new Stack<>();

        ListNode p = l1, q=l2;

        while ( p!=null ){
            stack1.push(p.val);
            p = p.next;
        }
        while ( q!=null ){
            stack2.push(q.val);
            q = q.next;
        }

        int flag = 0; //进位标记
        ListNode dummy = new ListNode(-1);
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            int x=0, y=0;
            if (!stack1.isEmpty()){
                x = stack1.pop();
            }
            if (!stack2.isEmpty()){
                y = stack2.pop();
            }

            int sum = x + y +flag;
            // 头插法
            ListNode backup = dummy.next;
            dummy.next = new ListNode(sum % 10, backup);
            flag = sum/10;

        }
        if (flag>0){
            dummy.next = new ListNode(flag, dummy.next);
        }

        return dummy.next;

    }

    // 解法二：翻转链表，相加，头插法形成结果链表
}
