package com.company.src.dynamicProgramming;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 * 链接：https://leetcode.cn/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence1143 {

    // 递归, 会超时，得改记忆化搜索
    public int longestCommonSubsequence(String text1, String text2) {
        memo = new int[text1.length()][text2.length()];
        for (int i = 0; i <text1.length() ; i++) {
            for (int j = 0; j < text2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return tryFind2(text1, text1.length()-1, text2, text2.length()-1);
    }

    /**
     * 解法一：递归，会超时
     * @param text1
     * @param index1
     * @param text2
     * @param index2
     * @return
     */
    public int tryFind(String text1, int index1, String text2, int index2) {
        if (index1<0 || index2<0)
            return 0;
        char a = text1.charAt(index1);
        char b = text2.charAt(index2);
        if (a==b)
            return 1+tryFind(text1, index1-1, text2, index2-1);
        else {
            return Math.max(
                    tryFind(text1, index1-1, text2, index2),
                    tryFind(text1, index1, text2, index2-1));
        }
    }


    /**
     * 解法二：记忆化搜索。
     */
    int[][] memo;
    public int tryFind2(String text1, int index1, String text2, int index2) {
        if (index1<0 || index2<0)
            return 0;
        if (memo[index1][index2]!=-1)
            return memo[index1][index2];

        char a = text1.charAt(index1);
        char b = text2.charAt(index2);
        int res =0 ;
        if (a==b)
            res = 1+tryFind(text1, index1-1, text2, index2-1);
        else {
            res = Math.max(
                    tryFind(text1, index1-1, text2, index2),
                    tryFind(text1, index1, text2, index2-1));
        }
        memo[index1][index2] =res;
        return res;
    }


    /**
     * 解法三：动态规划
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence3(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1+1][len2+1];  // 数组比len大一位，可以巧妙对避免i-1，j-1的问题。
        for (int i = 1; i <=len1 ; i++) {
            for (int j = 1; j <=len2 ; j++) {
                char a = text1.charAt(i-1);
                char b = text2.charAt(j-1);
                if (a==b){
                    dp[i][j] = 1+dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }

            }
        }
        return dp[len1][len2];
    }
}
