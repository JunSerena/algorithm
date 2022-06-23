package com.company.src.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。
 *
 * 返回 已排序的字符串 。如果有多个答案，返回其中任何一个。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "tree"
 * 输出: "eert"
 * 解释: 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入: s = "cccaaa"
 * 输出: "cccaaa"
 * 解释: 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 */
public class SortCharactersByFrequency451 {

    public static void main(String[] args) {
        System.out.println(frequencySort("aaxxxxxxxxxxxbbbcccccccc"));

    }

    public static String frequencySort(String s) { //s 由大小写英文字母和数字组成
        Map<Character, Integer>  map = new HashMap<>();
        int len=s.length();
        for (int i = 0; i < len; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }

        //利用Collections.sort函数 传入自定义比较器，对key进行排序，value大的在前
        List<Character> list = new ArrayList<>( map.keySet());
        Collections.sort(list,(a,b)->map.get(b)-map.get(a) );

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            int count = map.get(list.get(i));
            for (int j = 0; j <count ; j++) {
                stringBuilder.append(list.get(i));
            }
        }

        return stringBuilder.toString();
    }

}
