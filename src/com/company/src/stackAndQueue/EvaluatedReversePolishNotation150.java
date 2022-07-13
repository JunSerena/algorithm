package com.company.src.stackAndQueue;

import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 * 根据 逆波兰表示法，求表达式的值。
 *
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 注意 两个整数之间的除法只保留整数部分。
 *
 * 可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 *
 *
 * 示例 1：
 *
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例 2：
 *
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 *
 * 链接：https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 */
public class EvaluatedReversePolishNotation150 {
    public static void main(String[] args) {
        String uin = "100019870625";
        System.out.println(Long.valueOf(uin));
        String[] tokens = new String[]{"4","13","5","/","+"};
        System.out.println(evalRPN(tokens));
    }

    /**
     * 用栈，遇到运算符以后，弹出栈顶两个元素，计算以后再压栈
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        if ( tokens.length==1){
            return Integer.parseInt(tokens[0]);
        }

        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();

                if (s.equals("+")) {
                    stack.push(a + b);
                } else if (s.equals("-")) {
                    stack.push(a - b);
                } else if (s.equals("*")) {
                    stack.push(a * b);
                } else {
                    stack.push(a / b);
                }
            } else {
                stack.push(Integer.parseInt(s));
            }

        }
        return stack.pop();
    }
}
