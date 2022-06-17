package com.company.src.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 链接：https://leetcode.cn/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagrams438 {
    public static void main(String[] args) {
        List<Integer> result = findAnagrams("cbaebabacd", "abc");
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));

        }
    }

    // 滑动窗口解法
    // 用hashmap来存储窗口中的字母出现的频次，再比较两个hashmap是否相等
    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();

        // 特殊情况处理
        if (s.length()<p.length())
            return result;

        //用一个hashmap保存p的结构
        HashMap<Character, Integer> struct = new HashMap();
        HashMap<Character, Integer> windows = new HashMap();
        for (int i = 0; i <p.length() ; i++) {

            struct.put( p.charAt(i), struct.getOrDefault(p.charAt(i),0)+1);
            windows.put(s.charAt(i), windows.getOrDefault(s.charAt(i),0)+1);

        }
        if (struct.equals(windows)){
            result.add(0);
        }


        int len = p.length();
        for (int i = 0; i < s.length()-len; i++) {
            if (windows.getOrDefault(s.charAt(i),0) == 1 ){
                windows.remove(s.charAt(i));
            }else {
                windows.put(s.charAt(i), windows.getOrDefault(s.charAt(i),0)-1);
            }
            windows.put(s.charAt(i+len), windows.getOrDefault(s.charAt(i+len),0)+1);
            if (struct.equals(windows)) {
                result.add(i+1);
            }
        }
        return result;

    }


    // 滑动窗口解法
    // 用数组来表示字母出现频次，字母-'a'为索引，value为频次。比较两个数组是否相等
    public static List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int sLen =s.length(), pLen = p.length();

        if ( sLen < pLen){
            return result;
        }

        //构建两个数组
        int[] struct = new int[26];
        int[] windows = new int[26];
        for (int i = 0; i < pLen; i++) {
            struct[p.charAt(i)-'a']++;
            windows[s.charAt(i)-'a']++;
        }
        if (Arrays.equals(struct,windows)){
            result.add(0);
        }

        // 滑动窗口
        for (int i = 0; i < sLen-pLen; i++) {
            windows[s.charAt(i)-'a']--;
            windows[s.charAt(i+pLen)-'a']++;
            if (Arrays.equals(struct,windows)){
                result.add(i+1);
            }
        }

        return result;
    }


}
