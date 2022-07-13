package com.company.src.stackAndQueue;

/**
 * 最小栈
 * 方法二：使用单向链表实现。head指针作为栈顶。每个节点包含一个val和当前最小值。
 *
 * 注意点：pop和top，究竟要不要删除栈顶元素
 */
public class MinStack155_2 {
    /** initialize your data structure here. */
    Node head;
    public MinStack155_2() {
        head = null;
    }

    public void push(int val) {
        if (null == head)
            head = new Node(val,val);
        else {
            Node node =  new Node(val,Math.min(val,head.min));
            node.next = head;
            head = node;
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node{
        int val;
        int min;
        Node next;
        public Node(int val, int min){
            this.val = val;
            this.min = min;
            this.next = null;
        }
    }
}
