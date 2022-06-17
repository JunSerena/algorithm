package com.company.src.array;

import org.omg.PortableInterceptor.INACTIVE;

import javax.swing.*;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumWindowSubstring76 {

    public static void main(String[] args) {
        System.out.println(minWindow("b", "a"));
    }

    /**
     * 滑动窗口： 用两个map，map1保存t中字母出现的次数，map2保存在s窗口中的值且t中出现的
     *          当两个map的key相同，且map2对应的value大于等于map1中的value，视为两map相等。即map2包含了map1.
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()){
            return "";
        }

        HashMap<Character, Integer> struct = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            struct.put(t.charAt(i), struct.getOrDefault(t.charAt(i),0)+1);
        }

        int left=0, right=0; //保存最短子串 [left,right)
        String result="";
        int minLength = s.length()+1;

        while (left<s.length()){
            while (left<s.length() && !t.contains(s.charAt(left)+"")){  //负责找左边界
                    left++;
            }
            while (right<=s.length()){ //找定右边界，当窗口的map包含t的map时，停止。然后缩小左边界试图找最小覆盖子串。

                if (mapEquals(struct,window) ){
                    if ( minLength>right-left){
                        result = s.substring(left,right);
                        minLength = result.length();
                    }
                    break;
                }else {    // 右边界已经到了末尾，然后此窗口[left，right)还是没有覆盖子串，则直接结束了，left不用往后遍历了。
                    if (right==s.length()){
                        return result;
                    }
                }
                // t中出现的关键字才往窗口的map中放
                if (t.contains(s.charAt(right)+"")){
                    window.put(s.charAt(right), window.getOrDefault(s.charAt(right),0)+1);
                }
                right++;
            }

            window.put(s.charAt(left), window.getOrDefault(s.charAt(left),0)-1);
            left++;
        }
        return result;

    }

    public static boolean mapEquals(HashMap<Character, Integer> struct, HashMap<Character, Integer> window){
        if (struct.size()!=window.size()){
            return false;
        }
        Set<Character> characters = struct.keySet();
        for (char c : characters){
            if (window.get(c)==null || window.get(c) < struct.get(c)){
                return false;
            }
        }
        return true;
    }


    /**
     * 官方的滑动窗口解法
     */
    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();

    public String minWindow2(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

}
