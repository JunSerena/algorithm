package com.company.src.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *
 * 链接：https://leetcode.cn/problems/group-anagrams/
 */
public class GroupAnagrams49 {


    /**
     * 解法一：双重for遍历，用数组来比较两字符串是否是异位词。对于已经是异位词的元素，添加到set中，后续遇到的时候直接跳过。
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Set<Integer> set =new HashSet<>(); //用来剔除尚未遍历但已经是异位词的元素
        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            if (set.contains(i)){
                continue;
            }
            List<String> anagrams = new ArrayList<>();
            anagrams.add(strs[i]);
            for (int j = i+1; j <strs.length ; j++) {
                if (isAnagram(strs[i], strs[j])){
                    anagrams.add(strs[j]);
                    set.add(j);
                }

            }
            result.add(anagrams);

        }

        return result;

    }

    public static boolean isAnagram(String s, String t) {
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

    /**
     * 解法二：排序，排序以后字符串作为key，相同key的肯定是异位词
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i <strs.length ; i++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            List<String> list = map.getOrDefault(key,new ArrayList<>());
            list.add(strs[i]);
            map.put(key, list);
        }

        return new ArrayList<List<String>>(map.values());
    }
}
