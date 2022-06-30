package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

import java.util.List;
import java.util.Stack;

/**
 * 92 反转链表：给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 思路一：大致分成三节链表，中间那节链表做反转，反转思路（三指针，prev，current，next，将current指向的node节点的指针反转方向）麻烦，废弃
 * 然后连接三节链表
 *
 * ps: 需要重点考虑边界情况，如不能拆分三节，只能拆分两节或一节时
 *
 * 思路二：思路一有很多边界情况，不好。此处更好的做法是增加一个dummy node节点，这样就完全是可以拆分成三节/两节的，可以用三指针的方式来写了。
 *
 * 思路三：新增dummy node，（重点是前驱节点和当前节点一直都没变）
 *       以1->2->3->4->5, m = 2, n=4 为例:
 *       定位到要反转部分的头节点 2，head = 2；前驱结点 1，pre = 1；
 *       当前节点的下一个节点3调整为前驱节点的下一个节点 1->3->2->4->5, （实现步骤，先找到3，保留，使2->4,然后3指向2，1再指向3）
 *       当前结点仍为2， 前驱结点依然是1，重复上一步操作。。。
 *       1->4->3->2->5.
 *
 * 思路四：新增dummy node，找到left前的一个节点，将left-right入栈。
 */
public class ReverseLinkedList2_92 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static void main(String[] args) {
        // write your code here
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);

        reverseBetween7(head, 2, 4);
    }

    /**
     * 解法二，增加dummy 节点
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        //先处理异常情况
        if (null == head || left>right)
            return null;

        //设置一个dummy node，以确保最左边那段链表有节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //1、先找到left-1处的节点，即 最左边一节链表的最后一个节点，左边一节链表是不用动的
        ListNode prevLeft = dummy;  //起始值设置为dummy节点，确保左边一定有一个不动的节点
        for (int i=1; i<left; i++){   //从1起始，第 left-1个就是
            prevLeft = prevLeft.next;
        }
        //固定preLeft节点了

        //2、开始使用三指针翻转中间链表
        ListNode LeftNode = prevLeft.next;  //left处节点，不动，保留
        ListNode prev = prevLeft.next;  //prev指针
        ListNode current = prev.next;  //current指针，当前节点

        // ListNode next = current.next;  //当前节点的下一个节点。这行放在for循环里面，不然容易有空指针异常。

        for (int i= left; i<right; i++){  //起始值i=left就是left节点
            ListNode next = current.next;  //当前节点的下一个节点。
            current.next = prev; //改变链表指针走向

            prev = current;   //改变辅助的三指针，往后移动
            current = next;

        }//结束时，prev节点就是right处的节点

        //3、连接链表
        LeftNode.next = current;  //left的节点链接到right+1的节点即current节点
        prevLeft.next = prev; //prevLeft节点链接到right节点。

        return dummy.next;

    }

    /**
     * 解法三，增加dummy 节点，固定前驱节点和当前节点
     */
    public ListNode reverseBetween3(ListNode head, int left, int right) {
        //先处理异常情况
        if (null == head || left>right)
            return null;

        //设置一个dummy node，以确保最左边那段链表有节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //1、先找到left-1处的节点，即 最左边一节链表的最后一个节点，左边一节链表是不用动的
        ListNode prevLeft = dummy;  //起始值设置为dummy节点，确保左边一定有一个不动的节点
        for (int i=1; i<left; i++){   //从1起始，第 left-1个就是
            prevLeft = prevLeft.next;
        }

        //prevLeft即为前驱节点
        head = prevLeft.next;  //复用head节点，指代当前节点
        for (int i= left; i<right; i++) {  //起始值i=left就是left节点
            ListNode nxt = head.next;   //先保存当前节点的下一节点
            head.next = nxt.next;    //当前节点指向下下节点。因为下一节点需要反转到前驱节点的后面
            nxt.next = prevLeft.next; //下一节点指向前驱节点的后一个节点，是为了连接起整个链表
            prevLeft.next = nxt;  //下一节点需要反转到前驱节点的后面
        }


        return dummy.next;

    }


    /**
     * 用栈， 需要记录入栈前的节点，即left以前的节点prev，将left到right-1的节点全部入栈，将prev.next指向right处节点，如果prev为空，那么right处节点成为新的头节点。
     *
     * 只需要遍历到right处节点就可以结束遍历了。
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween4(ListNode head, int left, int right) {
        if ( null == head || head.next ==null || left==right)
            return head;

        ListNode current = head;

        int count = 0;
        Stack<ListNode> stack = new Stack<>();
        ListNode start = null; //left处的前一个节点

        while (current!=null){
            count++;
            if (count == left-1){
                start = current;  //保存入栈前的一个节
            }else if (count >= left && count < right){
                stack.push(current);
            }else if (count==right){
                // 所有该反转的都已经入栈了,current未入栈，但是current是第一个反转的节点
                if (start==null){
                    head = current; //如果说left=1，那么prev是不会被赋值的，也就是说，right处的节点会成为新的头节点
                }else {
                    start.next = current;  // 否则的话，就从prev节点处连接到right节点
                }
                //记录翻转结束后面的节点
                ListNode backup = current.next;

                // 开始翻转链表
                while (!stack.isEmpty()){
                    current.next = stack.pop();
                    current = current.next;
                }
                current.next = backup;
                break;
            }

            current = current.next;

        }

        return head;

    }


    /**
     * 还是用栈，增加一个前驱dummy节点。这样就避免了left处节点的前驱节点为空节点。
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween5(ListNode head, int left, int right) {
        if (null == head || head.next == null || left == right)
            return head;

        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(0, head);
        ListNode current = dummy, start=dummy;
        int count =0;

        while (current!=null){
            if (count==left-1){
                start = current;
            }else if (count>=left && count < right){
                stack.push(current);
            }else if (count==right){
                //开始翻转链表
                start.next = current;
                ListNode backup = current.next;
                while (!stack.isEmpty()){
                    current.next = stack.pop();
                    current = current.next;
                }
                current.next = backup;
                break;
            }

            current = current.next;
            count++;

        }
        return dummy.next;
    }


    /**
     * 重写思路二
     * 用三指针和dummy节点 同样也需要先找到left前一个节点start节点，然后三指针翻转left-right处节点。需要注意衔接left节点的next到right的后一个节点，以及start节点的next到right节点
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween6(ListNode head, int left, int right) {
        if (null == head || head.next == null || left == right)
            return head;

        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy, current= dummy.next;
        ListNode start = dummy; // left的前一个节点，翻转后他的next指针需要指向right处节点

        int count = 0;

        while (current!=null){
            count++;
            if (count == left-1){
                start = current;
            }else if (count>=left && count<right){
                //先备份一下current的next，
                ListNode backup = current.next;
                current.next = prev;
                prev = current;
                current = backup;
                continue;
            }else if (count == right){
                //left处的节点需要衔接到right节点后一截 start.next是left处节点
                start.next.next = current.next;
                current.next = prev; //最后一个right节点翻转
                start.next = current; //再将right处节点衔接上前面未翻转的节点
                break;
            }

            prev = current;
            current = current.next;

        }
        return  dummy.next;
    }


    /**
     * * 思路三：新增dummy node，（重点是前驱节点和当前节点一直都没变）
     *  重写思路三
     */
    public static ListNode reverseBetween7(ListNode head, int left, int right) {
        if (null == head || head.next == null || left == right)
            return head;

        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy, current= dummy.next;
        //ListNode start = dummy; // left的前一个节点，他的next指针需要指向right处节点

        //先定位到前驱节点prev和当前节点
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }
        current = prev.next; //left处的节点

        //以left处节点为旋转轴，将left的下一个节点放到pre的下一个节点处
        for (int i = left; i < right; i++) {
            ListNode next = current.next;
            current.next = next.next;

            next.next = prev.next;
            prev.next = next;

        }

        return dummy.next;

    }
}
