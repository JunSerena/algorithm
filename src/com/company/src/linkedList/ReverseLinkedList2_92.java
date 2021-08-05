package com.company.src.linkedList;

/**
 * 92 反转链表：给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 思路一：大致分成三节链表，中间那节链表做反转，反转思路（三指针，prev，current，next，将current指向的node节点的指针反转方向）
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
     * 解法一：这个解法还有bug,以后再调吧。。。。
     * @param head
     * @param left
     * @param right
     * @return
     */
    //链表中节点数目为 n
    //1 <= n <= 500
    //-500 <= Node.val <= 500
    //1 <= left <= right <= n
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //1、重合，不必翻转
        if (left==right)
            return head;

        //先找到位置left的节点的前一个节点
        ListNode leftNode = head;
        for (int i=1; i<left-1; i++){   //left-1处的节点为第一节链表的尾部
            leftNode = leftNode.next;
        }
        //保留第一节链表的尾部，若不存在第一节链表，
        ListNode part1 = null;
        if (leftNode != head) {
            part1 = leftNode;
        }

        //中间的链表反转：主要思想，逆转current节点的指针
        leftNode = leftNode.next;  //中间链表的开头 保留 即为反转后链表的尾部
        ListNode prev = leftNode;
        ListNode current = leftNode.next;
        for (int i=left; i<right; i++){ // pre节点位置为i,pre走到right节点，current节点为第三节链表的开头。
            ListNode next = current.next;
            //逆转指针
            current.next = prev;
            //下一步
            prev = current;
            current = next;
        }

        //第一节链表尾部连接第二节链表的反转后的头部
        if (part1 != null)
            part1.next = prev;

        //第二节链表的反转后的尾部连接第三节的头部
        leftNode.next = current;
        return head;

    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
