package com.company.linkedList;

import java.util.HashMap;

/**
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 深拷贝
 * 解法一：使用hashmap保存 旧节点和新节点之间的映射关系，再组装新节点之间的连接关系
 *
 * 解法二：解法一的问题，空间复杂度高，hashmap。
 *       关键点：如何保存旧节点和新节点之间的映射关系
 *       在旧节点后插入一个新的节点，如A->B->C, 变成A->A1->B->B1->C->C1.
 *       然后把随机指针也做响应的拷贝。
 *       最后把新旧节点拆开成两个链表
 */
public class CopyLinkedListWithRandomPointer138 {

    //解法一,hashmap, 空间复杂度O(n)
    public Node copyRandomList1(Node head) {
        if (null == head)
            return null;
        HashMap<Node,Node> map = new HashMap<>();
        Node cur = head;
        //建立映射关系
        while (cur != null){
            if (!map.containsKey(cur)){
                Node copy = new Node(cur.val);
                map.put(cur,copy);
            }
            cur = cur.next;
        }

        //构建新链表
        Node old = head;
        Node newOne = map.get(old);
        while (old!=null){
            newOne.next = map.get(old.next);
            newOne.random = map.get(old.random);
            old = old.next;
            newOne = newOne.next;
        }

        return map.get(head);

    }
    //解法二,原地处理，将克隆结点放在原结点后面，在原链表上处理克隆结点的random指针，最后分离两个链表
    public Node copyRandomList2(Node head) {
        if (null == head)
            return null;
        copy(head);
        copyRandom(head);
        return split(head);
    }

    private void copy(Node head){
        Node current = head;
        while (current != null){
            Node copy = new Node(current.val);
            //将新节点插入旧节点之后
            copy.next = current.next;
            current.next = copy;
            current = copy.next;
        }
    }
    private void copyRandom(Node head){
        Node current = head;
        while (current != null && current.next!=null){  //current是旧节点，current.next是新节点
            if (current.random != null){
                //拷贝random之间的关系(新节点的random是旧节点random的下一个节点)
                current.next.random = current.random.next;
            }
            //到下一个旧节点去
            current = current.next.next;
        }
    }

    private Node split(Node head){
        Node old = head;
        Node newHead = head.next;
        Node current = newHead;
        //old用于遍历旧链表，current用于遍历新链表
        while (old!=null && old.next!=null){
            old.next = old.next.next;  //将A->A1->B->B1变成 A->B
            old = old.next;  //old循环至下一个旧节点，即B
            if (current!=null && current.next!=null){
                current.next = current.next.next; //将A->A1->B->B1变成 A1->B1
                current = current.next;
            }
        }
        return newHead;
    }


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
