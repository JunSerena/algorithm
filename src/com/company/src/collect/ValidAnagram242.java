package com.company.src.collect;

import java.util.HashMap;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * 链接：https://leetcode.cn/problems/valid-anagram/
 */
public class ValidAnagram242 {

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram","nagaram"));
    }

    //解法1：利用map统计频次
    public static boolean isAnagram(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        if (len1!=len2){
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i <len1 ; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0)+1);
        }

        for (int i = 0; i <len2 ; i++) {
            if (map.containsKey(t.charAt(i))){
                if (map.get(t.charAt(i))==1)
                    map.remove(t.charAt(i));
                else
                    map.put(t.charAt(i),map.get(t.charAt(i))-1);
            }else
                return false;
        }

        return map.size()==0;
    }

    // 解法二：因为字符串只包含小写字母，所以也可以用数组来统计频次。
    public boolean isAnagram2(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        if (len1!=len2){
            return false;
        }

        int[] count = new int[26];
        for (int i = 0; i <len1 ; i++) {
            count[s.charAt(i)-'a']++;
            count[t.charAt(i)-'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i]!=0)
                return false;
        }
        return true;
    }

}
