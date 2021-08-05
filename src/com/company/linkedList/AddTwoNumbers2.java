package com.company.linkedList;

public class AddTwoNumbers2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1)
            return l2;
        if (null == l2)
            return l1;

        ListNode one = l1;
        ListNode two = l2;
        ListNode sum = new ListNode(0);
        ListNode current = sum;
        int cary = 0;
        int num = 0;
        //两个链表都有值的时候
        while (one != null && two != null) {
            num = one.val+two.val+cary;
            ListNode node = new ListNode(num%10);
            cary = num/10;
            one = one.next;
            two =two.next;
            current.next = node;
            current = current.next;
        }
        //某个链表更短，已经结束
        while (one != null){
            num = one.val+cary;
            ListNode node = new ListNode(num%10);
            cary = num/10;
            one = one.next;
            current.next = node;
            current = current.next;
        }

        while (two != null){
            num = two.val+cary;
            ListNode node = new ListNode(num%10);
            cary = num/10;
            two = two.next;
            current.next = node;
            current = current.next;
        }

        //最后可能存在进位
        if (cary!=0){
            current.next = new ListNode(cary);
        }
        return sum.next;

    }

    /**
     * 两年前写的代码，看起来是更简洁的
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    //Definition for singly-linked list.
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
