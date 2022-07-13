package com.company.src.stackAndQueue;

import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 *
 * 思路：
 * 方法一：使用辅助栈
 * 使用两个栈，一个栈做正常的栈，一个用以保存最小值minStack。
 * push：栈为空时，两个栈插入相同元素，栈非空时，minStack插入 min(current,栈顶元素)
 * pop: 删除两个栈的栈顶元素
 * getMin: minStack的栈顶元素
 *
 * 方法二：使用单向链表实现。head指针作为栈顶。每个节点包含一个val和当前最小值。
 */
public class MinStack155 {
    Stack<Integer> stack;
    Stack<Integer> minStack;


    /** initialize your data structure here. */
    public MinStack155() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();

    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty())
            minStack.push(val);
        else
            minStack.push(Math.min(val,minStack.peek()));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }


}
