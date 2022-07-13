package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

import java.util.Stack;

/**
 * 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 示例 2：
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 链接：https://leetcode.cn/problems/reverse-nodes-in-k-group/
 * 难度：困难
 */
public class ReverseNodesInKGroup25 {
    public static void main(String[] args) {

//        ListNode node5 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        ListNode node3 = new ListNode(4, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(2, node2);
        ListNode head = new ListNode(1, node1);

        reverseKGroup4(head, 3);

    }

    //解法一：用栈，栈内每压入k个元素，抛出翻转链表。最后栈内不成k个元素，抛出无需翻转
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (null == head || k==1)
            return head;
        ListNode dummy = new ListNode(0, head), pre = dummy, current = dummy.next;
        Stack<ListNode> stack = new Stack<>();

        while (current!=null){
            // 小于k 压栈
            ListNode next = current.next;
            if (stack.size() < k){
                current.next = null;  //压栈前先清空节点的next，防止后面翻转时成环
                stack.push(current);

            }
            if (stack.size() == k){   //等于k，翻转链表
                while (!stack.isEmpty()){
                    pre.next = stack.pop();
                    pre = pre.next;
                }
                //衔接翻转链表和后续节点
                pre.next = next;  // pre是翻转链表的前一个节点
            }

            current = next;
        }
        // 末尾，未到k个，不需要翻转
        pre.next = null; //pre后面衔接的先清空。再重新插入栈内元素。防止成环
        while (!stack.isEmpty()){
            // 不需要翻转，所以走头插法
            ListNode cur =  stack.pop();
            cur.next = pre.next;
            pre.next = cur;

        }

        return dummy.next;

    }

    // 解法二：还是用栈，优化解法一
    // 每次翻转成功后，翻转完成的链表会衔接上后续未翻转的链表。因此最后是不需要处理栈内剩余元素的。
    public static ListNode reverseKGroup2(ListNode head, int k) {
        if (null == head || k==1)
            return head;
        ListNode dummy = new ListNode(0, head), pre = dummy, current = dummy.next;
        Stack<ListNode> stack = new Stack<>();

        while (current!=null){
            ListNode next = current.next; //备份后续节点

            // 小于k 压栈
            if (stack.size() < k-1){
                stack.push(current);
            }else if (stack.size() == k-1){   //栈内元素个数等于k-1，说明current是第k个了，翻转链表
                pre.next = current;
                pre = pre.next;
                while (!stack.isEmpty()){
                    ListNode top = stack.pop();
                    top.next = null; //清空next，防止成环
                    pre.next = top;
                    pre = pre.next;
                }
                //衔接翻转链表和后续节点
                pre.next = next;
            }

            current = next;
        }

        return dummy.next;

    }




    // 三指针翻转/头插法
    public static ListNode reverseKGroup4(ListNode head, int k) {
        if (null == head || k==1)
            return head;

        ListNode dummy = new ListNode(0, head), pre = dummy, current = dummy.next;
        while (true){
            ListNode tail = current;
            for (int i = 0; i <k ; i++) {
                if (tail==null){
                    return dummy.next;
                }
                tail = tail.next;
            }

            // 翻转k个链表 pre和current固定，后面的不断往pre后面插入
            for (int i = 0; i <k-1 ; i++) {  //设置k-1是因为需要把next插入到pre和current之间。
                ListNode next = current.next;
                current.next = next.next;
                next.next = pre.next;  // pre.next不一定是current，中间还有其他的翻转节点。
                pre.next = next;
            }
            pre = current;
            current = current.next;
        }

    }

}