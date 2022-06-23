package com.company.src.collect;

import java.util.HashMap;
import java.util.Map;

/**
 *290. 单词规律
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 *
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 *
 * 链接：https://leetcode.cn/problems/word-pattern/
 */
public class WordPattern290 {

    public boolean wordPattern(String pattern, String s) {
        String[] arr = s.split(" "); //s按空格分割
        if (arr.length != pattern.length())
            return false;

        // 需要注意的是，不同的key不能对应同一个value
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i <pattern.length() ; i++) {
            char p = pattern.charAt(i);
            if (map.containsKey(p) ){
                if (!map.get(p).equals(arr[i])){
                    return false;
                }
            }else {
                if (map.containsValue(arr[i])){
                    return false;
                }
                map.put(p, arr[i]);
            }
        }

        return true;
    }
}
