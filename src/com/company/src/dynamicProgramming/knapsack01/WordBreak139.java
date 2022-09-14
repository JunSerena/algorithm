package com.company.src.dynamicProgramming.knapsack01;

import java.util.ArrayList;
import java.util.List;

/**
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 *
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 链接：https://leetcode.cn/problems/word-break/
 */
public class WordBreak139 {

    public static void main(String[] args) {

        WordBreak139 ins = new WordBreak139();
        String s = "cars";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("car");
        wordDict.add("ca");
        wordDict.add("rs");
        System.out.println(ins.wordBreak2(s, wordDict));
    }

    /**
     * 解法一：递归+记忆化搜索，[index, len)是否能找到可以组成substring的
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new int[s.length()];
        return tryBreak(s, 0, wordDict);
    }

    int[] memo;
    public boolean tryBreak(String s, int index, List<String> wordDict) {
        if (index==s.length()){
            return true;
        }
        if (index>s.length()){
            return false;
        }
        if (memo[index]!=0)
            return memo[index]==1;

        int size = wordDict.size();
        for (int i = 0; i <size ; i++) {
            String word = wordDict.get(i);

            int end = index+word.length();
            if (end > s.length())
                continue;
            String subString = s.substring(index, end);
            if (word.equals(subString) && tryBreak(s, end, wordDict)){
                memo[index]=1;
                return true;
            }
        }
        memo[index]=2;
        return false;
    }


    /**
     * 解法二：动态规划，判断[0,index]处能否找到可组成该子串的元素。转移方程
     * F(i) = 当[i-word.length, i]处子串=word时的F(i-word.length) 因为不知道word是哪一个，就遍历wordDict，为true就停止
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        int len = s.length();
        int size = wordDict.size();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <size ; j++) {
                String word = wordDict.get(j);
                if (word.length()<=i && word.equals(s.substring(i-word.length(), i))){
                    dp[i] = dp[i-word.length()];
                    if (dp[i]){ // 为true说明[0, i]的子串已经找到了对应构成的word，可以结束i下的寻找
                        break;
                    }
                }
            }
        }
        return dp[len];
    }
}
