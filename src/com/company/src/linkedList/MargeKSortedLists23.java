package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 * 难度：困难
 *
 * 链接：https://leetcode.cn/problems/merge-k-sorted-lists/
 */
public class MargeKSortedLists23 {


    /**
     * 解法一：利用优先队列/小顶堆。获取k个链表中最小的元素，再把他的next推入链表中
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        // 特殊处理：lists中没有元素 []
        if (lists.length==0){
            return null;
        }

        int size = lists.length;
        //维护长度为k的小顶堆
        PriorityQueue<ListNode> queue = new PriorityQueue<>(size, (a,b)-> a.val-b.val);

        // 特殊处理：lists中有空元素[[]]
        for (int i = 0; i < size ; i++) {
            if (lists[i]!=null){
                queue.add(lists[i]);
            }
        }

        //queue.addAll(Arrays.asList(lists));
        ListNode dummy = new ListNode(0), prev = dummy, cur;
        while (!queue.isEmpty()){
            cur = queue.poll();
            prev.next = cur;
            prev = cur;
            if (cur.next!=null){
                queue.add(cur.next);
            }
        }

        return dummy.next;

    }

    /**
     * 解法二：普通顺序合并，链表1和2合并以后，再和3合并，再和4合并
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        int size = lists.length;

        ListNode ans = null;
        for (int i = 0; i <size ; i++) {
            ans = mergeTwoLists(ans, lists[i]);
        }

        return ans;

    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        ListNode node13 = new ListNode(5);
        ListNode node12 = new ListNode(4, node13);
        ListNode node11 = new ListNode(1, node12);

        ListNode node23 = new ListNode(4);
        ListNode node22 = new ListNode(3, node23);
        ListNode node21 = new ListNode(1, node22);

        ListNode node32 = new ListNode(6);
        ListNode node31 = new ListNode(2, node32);

        lists[0]= node11;
        lists[1]= node21;
        lists[2]= node31;

        mergeKLists3_1(lists);

    }

    /**
     * 解法三：两两归并排序，自底向上归并（通过，但是被弃，不太好理解）
     * @param lists
     * @return
     */
    public static ListNode mergeKLists3(ListNode[] lists) {
        int len = lists.length;
        if (len==0)
            return null;

        int count = len/2; //有多少组(两两为一组)链表需要合并：如果是3个链表，那就是count =1, 前两个为1组合并，最后一个链表和填充的null合并
        boolean flag = len%2==1; // 末尾是否需要填充一个空链表

        while (count != 0 ){

            for (int i = 0; i < count; i++) {
                lists[i]=mergeTwoLists(lists[i*2],lists[i*2+1]);
            }
            if (flag) {  // 奇数个元素，最后一个肯定和null合并
                lists[count] = mergeTwoLists(lists[2 * count], null);
                flag = (count+1)%2==1;
                count = (count+1)/2;  // 填充以后，其实是 count+1组元素进行了合并，合并后还剩下 count+1 /2 组元素需要合并

            }else {
                flag = count%2==1;
                count = count/2;
            }
        }

        return lists[0];
    }

    /**
     * 解法三-2，优化改造，解法三的写法不好理解。还是两两归并，自底向上归并
     * @param lists
     * @return
     */
    public static ListNode mergeKLists3_1(ListNode[] lists) {
        int len = lists.length;
        if (len==0)
            return null;

        int count = len, nextCount = 0; //count 本次迭代需要合并的列表个数，nextCount下次迭代需要合并的列表个数

        while (count != 1 ){

            for (int i = 0; i < count; i=i+2) {
                if (i==count-1){   //count为奇数时
                    lists[nextCount]=mergeTwoLists(lists[i],null);
                }else { // 偶数两两合并
                    lists[nextCount]=mergeTwoLists(lists[i],lists[i+1]);
                }
                nextCount++;
            }
            count = nextCount;
            nextCount = 0;
        }

        return lists[0];
    }


    /**
     * 解法四：官方解法：分治合并
     * @param lists
     * @return
     */
    public ListNode mergeKLists4(ListNode[] lists) {
        return merge(lists, 0, lists.length-1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }




    // 合并两个链表
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1==null)
            return list2;
        if (list2==null)
            return list1;

        ListNode dummy = new ListNode(0), prev = dummy;
        ListNode node1 = list1, node2 =list2;
        while (node1!=null && node2!=null){
            if (node1.val>node2.val){
                prev.next = node2;
                prev = prev.next;
                node2 = node2.next;
            }else {
                prev.next = node1;
                prev = prev.next;
                node1 = node1.next;
            }

        }

        if (node1==null){
            prev.next = node2;
        }else {
            prev.next = node1;
        }

        return dummy.next;
    }


}
