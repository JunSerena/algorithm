package com.company.src.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * 链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfAPhoneNumber17 {

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber17 ins = new LetterCombinationsOfAPhoneNumber17();
        List<String> strings = ins.letterCombinations("23");
        System.out.println(strings);
    }

    HashMap<String, List<String>> map = buildMap();

    /**
     * 解法一：找出递归结构，当前数字串包含的字符串列表=第一个数字表示的字符串+子数字串表示的列表。当子数字串为1个数字时，结束递归。
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits==null || digits.equals(""))
            return new ArrayList<>();
        if (digits.length()==1){
            return map.get(digits.charAt(0)+"");
        }

        List<String> letters = map.get(digits.charAt(0)+"");
        List<String> subString = letterCombinations(digits.substring(1));

        List<String>  result = new ArrayList<>();

        for (String letter: letters) {
            for(String str: subString) {
                result.add(letter+str);
            }
        }
        return result;
    }

    public static HashMap<String, List<String>> buildMap(){
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("2", Arrays.asList("a","b","c"));
        map.put("3", Arrays.asList("d","e","f"));
        map.put("4", Arrays.asList("g","h","i"));
        map.put("5", Arrays.asList("j","k","l"));
        map.put("6", Arrays.asList("m","n","o"));
        map.put("7", Arrays.asList("p","q","r","s"));
        map.put("8", Arrays.asList("t","u","v"));
        map.put("9", Arrays.asList("w","x","y","z"));
        return map;
    }
}
