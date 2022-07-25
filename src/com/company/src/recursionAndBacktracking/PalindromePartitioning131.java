package com.company.src.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 131. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 * 链接：https://leetcode.cn/problems/palindrome-partitioning/
 */
public class PalindromePartitioning131 {
    public static void main(String[] args) {
        String s = "aa";
        List<List<String>> list = partition(s);
        System.out.println(list);
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        if (s.length()==1){
            List<String> list = new ArrayList<>();
            list.add(s);
            result.add(list);
            return result;
        }

        for (int i = 1; i <=s.length() ; i++) {
            if (i==s.length() && isPalindrome(s)){
                List<String> list = new ArrayList<>();
                list.add(s);
                result.add(list);
                return result;
            }

            String first = s.substring(0,i);
            if (isPalindrome(first)){
                List<List<String>>  lists = partition(s.substring(i));
                for ( List<String> l: lists) {
                    l.add(0, first);
                }
                result.addAll(lists);
            }
        }


        return result;

    }

    public static boolean isPalindrome(String s){
        if (s.length()==1)
            return true;
        int l = 0, r=s.length()-1;
        while (l<r){
            if (s.charAt(l)!=s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
}
