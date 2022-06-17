package com.company.src.array;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstring3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    // 滑动窗口解法
    public static int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        HashSet<Character> hashMap = new HashSet<>();

        int left =0, right = 0; //[left,right)区间
        int len = arr.length, dif = 0;
        while (left <= right && right< len){
            if (hashMap.contains(arr[right])){
                hashMap.remove(arr[left]);
                left++;
            }else {
                hashMap.add(arr[right]);
                right++;
                dif = Math.max(dif, right-left);
            }
        }
        return dif;
    }
}
