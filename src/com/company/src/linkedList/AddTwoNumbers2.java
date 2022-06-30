package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

public class AddTwoNumbers2 {

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



    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode current1 = l1, current2 =l2;
        int flag = 0, newVal =0; //进位标志
        ListNode  sumHead=new ListNode(-1), tail = sumHead;

        while (current1!=null || current2!=null){

            if (current1!=null && current2!=null){
                int sum = current1.val+current2.val+flag;
                newVal = sum%10;
                flag = sum/10;
                current1 = current1.next;
                current2 = current2.next;

            }else if (current1 != null){
                int sum = current1.val+flag;
                newVal = sum%10;
                flag = sum/10;
                current1 = current1.next;

            }else {
                int sum = current2.val+flag;
                newVal = sum%10;
                flag = sum/10;
                current2 = current2.next;
            }


            tail.next = new ListNode(newVal);
            tail = tail.next;

        }
        if (flag > 0){
            tail.next = new ListNode(flag);
        }

        return sumHead.next;

    }

    public ListNode addTwoNumbers4(ListNode l1, ListNode l2) {

        ListNode current1 = l1, current2 =l2;
        int flag = 0; //进位标志
        ListNode  sumHead=new ListNode(-1), tail = sumHead;
        while (current1!=null || current2!=null){
            int x=0,y=0;
            if (current1 !=null ){
                x = current1.val;
                current1 = current1.next;
            }
            if (current2 !=null ){
                y = current2.val;
                current2 = current2.next;
            }
            int sum = x + y + flag;
            flag = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
        }
        if (flag > 0){
            tail.next = new ListNode(flag);
        }
        return sumHead.next;

    }















































}
