package com.company.src.stack;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 *
 * 思路：
 * 栈的使用,主要思想是看当前括号与栈顶括号是否匹配，匹配则出栈，不匹配就入栈
 * 注意空栈的情况
 *
 */
public class ValidParentheses20 {
    public static void main(String[] args){
        isValid("()[]{}");
    }

    //解法一：遍历字符。
    public static boolean isValid(String s) {
        if (null == s || s.length() == 0)
            return true;
        Stack<Character> stack = new Stack<>();
        int length = s.length();

        for (int i=0; i<length; i++){
            if (stack.isEmpty()){
                stack.push(s.charAt(i));
            }else if (isCouple(stack.peek(), s.charAt(i))){
                stack.pop();
            }else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    public static boolean isCouple(Character a, Character b){
        return (a == '(' && b == ')') || (a == '{' && b == '}') || (a == '[' && b == ']');
    }

    //解法二：遍历字符。但是部分情况可以提前终止，直接返回false，可节省时间
    public  boolean isValid2(String s) {
        if (null == s || s.length() == 0)
            return true;
        Stack<Character> stack = new Stack<>();
        int length = s.length();

        for (int i=0; i<length; i++){
            char c = s.charAt(i);
            if (c=='{' || c=='(' || c=='['){
                stack.push(c);
                continue;
            }
            if (c == '}' ){
                if (stack.isEmpty() || stack.pop()!='{')
                    return false;
            }
            if (c == ')' ){
                if (stack.isEmpty() || stack.pop()!='(')
                    return false;
            }
            if (c == ']' ){
                if (stack.isEmpty() || stack.pop()!='[')
                    return false;
            }

        }
        return !stack.isEmpty();
    }

    //解法三：评论里看到的挺好玩的解法
    //识别到左括号的时候把右括号压栈
    public  boolean isValid3(String s) {
        if (null == s || s.length() == 0)
            return true;
        Stack<Character> stack = new Stack<>();
        int length = s.length();

        for (int i=0; i<length; i++){
            if (s.charAt(i)=='(')
                stack.push(')');
            else if (s.charAt(i)=='{')
                stack.push('}');
            else if (s.charAt(i)=='[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop()!=s.charAt(i))
                return false;
        }
        return stack.isEmpty();
    }
}
