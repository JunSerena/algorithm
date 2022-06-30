package com.company.src.linkedList;

import com.company.src.linkedList.util.ListNode;

/**
 * 237. 删除链表中的节点
 * 请编写一个函数，用于 删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
 *
 * 题目数据保证需要删除的节点 不是末尾节点 。
 *
 *
 * 示例 1：
 *
 * 输入：head = [4,5,1,9], node = 5
 * 输出：[4,1,9]
 * 解释：指定链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
 * 示例 2：
 *
 * 输入：head = [4,5,1,9], node = 1
 * 输出：[4,5,9]
 * 解释：指定链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9
 *
 * 链接：https://leetcode.cn/problems/delete-node-in-a-linked-list/
 */
public class DeleteNodeInALinkedList237 {
    /**
     * 只给待删除node，不给头节点
     * 将下一个节点的值拷贝过来，再删除下一个节点
     * @param node
     */
    public void deleteNode(ListNode node) {
        if (node==null){
            return;
        }
        if (node.next == null){
            node = null;
            return;
        }
        //题目保证了node不是最后一个
        node.val = node.next.val;
        node.next = node.next.next;

    }
}
