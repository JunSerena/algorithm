package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 *
 * 示例 1：
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 *
 *
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 * 链接：https://leetcode.cn/problems/sort-list/
 */
public class SortList148 {
    /**
     * 解法一：自底向上归并排序：
     * 具体做法如下。
     *
     * 用 subLength 表示每次需要排序的子链表的长度，初始时 subLength=1。
     *
     * 每次将链表拆分成若干个长度为 subLength 的子链表（最后一个子链表的长度可以小于 subLength），按照每两个子链表一组进行合并，合并后即可得到若干个长度为 subLength×2 的有序子链表（最后一个子链表的长度可以小于subLength×2）。
     * 合并两个子链表仍然使用「21. 合并两个有序链表」的做法。
     *
     * 将subLength 的值加倍，重复第 2 步，对更长的有序子链表进行合并操作，直到有序子链表的长度大于或等于 length，整个链表排序完毕。
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head==null || head.next == null){
            return head;
        }
        ListNode last = head;
        // 求出链表总长度
        int length = 0;
        while (last!=null){
            length++;
            last = last.next;
        }

        int subLength = 1;
        ListNode dummyHead = new ListNode(0, head);

        while (subLength<length){

            ListNode prev = dummyHead, curr = dummyHead.next;

            // 每两个subLength长度的链表归并一次，链接到prev节点。prev遍历到归并链表的末尾。
            while (curr != null){
                //找到第一个头节点
                ListNode head1= curr;
                for (int i = 1; i <subLength && curr.next!=null; i++) {  //要用curr.next != null是为了锁定head2
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;  //第一个链表的尾节点

                // 找第二个链表
                curr = head2; //curr可能为null
                for (int i = 1; i <subLength && curr!=null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr!=null){
                    next = curr.next;
                    curr.next = null;  //第二个链表的尾节点置空
                }

                // 链表归并
                //链接归并完的链表，将prev指针指到最后
                prev.next = mergeList(head1, head2);
                while (prev.next!=null){
                    prev = prev.next;
                }
               //prev.next = next;
                curr = next;
            }

            subLength = subLength << 1; // subLength扩成两倍

        }
        return dummyHead.next;

    }


    //官方解答：自底向上归并排序
    public ListNode sortList2(ListNode head) {
        if (head == null) {
            return head;
        }
        // 找到链表长度
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);

        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr; //第一个链表头
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null; // 第一个链表尾设置为null

                curr = head2; //第二个链表头
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next; //先保存next 再给第二个链表末尾置空
                    curr.next = null;
                }
                ListNode merged = mergeList(head1, head2);
                // 找到合并好的链表末尾为prev
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }


    /**
     * 解法二：自顶向下归并排序：
     * 对链表自顶向下归并排序的过程如下。
     *
     * 找到链表的中点，以中点为分界，将链表拆分成两个子链表。寻找链表的中点可以使用快慢指针的做法，快指针每次移动 2 步，慢指针每次移动 1 步，当快指针到达链表末尾时，慢指针指向的链表节点即为链表的中点。
     *
     * 对两个子链表分别排序。
     *
     * 将两个排序后的子链表合并，得到完整的排序后的链表。可以使用「21. 合并两个有序链表」的做法，将两个有序的子链表进行合并。
     *
     * 上述过程可以通过递归实现。递归的终止条件是链表的节点个数小于或等于 1，即当链表为空或者链表只包含 1 个节点时，不需要对链表进行拆分和排序。
     *
     * @param head
     * @return
     */
    public static ListNode sortList3(ListNode head) {
        return sortList(head, null);
    }

    // tail只是用来标记此段链表是否到达尾部，tail并不计入该段链表中。所以首次传入的是null
    public static ListNode sortList(ListNode head, ListNode tail) {
        if (head==null)
            return head;
        if (head.next == tail){
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = slow;
        while (fast!=tail){
            slow = slow.next;
            fast = fast.next;
            if (fast!=tail){
                fast = fast.next;
            }
        }
        ListNode list1 = sortList(head, slow);
        ListNode list2 = sortList(slow, tail);
        return mergeList(list1, list2);
    }


    public static ListNode mergeList(ListNode head1,ListNode head2 ){
//        tail1.next = null;
//        tail2.next = null;
        ListNode dummy = new ListNode(0), sort = dummy;
        while (head1 != null && head2!=null){

            if (head1.val<=head2.val){
                sort.next = head1;
                head1 = head1.next;

            }else {
                sort.next = head2;
                head2 = head2.next;
            }
            sort = sort.next;

        }
        //两链表至少有一个到达了尾部
        if (head1==null){
            sort.next = head2;
        }else {
            sort.next = head1;
        }

        return dummy.next;
    }

    /**
     * 解法三：快排。利用类似86号题目思路，将比标定点小的都放在dummy后面，大的都放在标定点后。递归。
     * 需要注意的是：递归时一定要传入dummy节点，才能保证节点在原链表上移动。
     * @param head
     * @return
     */
    public static ListNode sortList4(ListNode head) {

        ListNode dummy = new ListNode(-11111, head);
        return partitionSort(dummy, null);
    }

    // dummy哨兵节点不参与运算，只作为头节点标志 ，tail不参与运算，作为是否抵达末尾的标志
    public static ListNode partitionSort(ListNode dummy, ListNode tail){
        if (dummy==null)
            return dummy;
        if (dummy.next==tail){
            return  dummy.next;
        }

        ListNode head = dummy.next, prev = dummy.next, curr = prev.next;
        //head作为标定点，比head小的放dummy后，比head大的放head后。
        while (curr!=tail){
            ListNode next = curr.next;
            if (curr.val<head.val){
                prev.next = next;
                curr.next = dummy.next;
                dummy.next = curr;
            }else {
                prev = prev.next;
            }
            curr = next;
        }

        partitionSort(dummy, head);
        partitionSort(head, tail);

        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode node5 = new ListNode(6);
        ListNode node4 = new ListNode(0, node5);
        ListNode node3 = new ListNode(4, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(5, node2);
        ListNode head = new ListNode(-1, node1);

        sortList4(head);
    }


}
