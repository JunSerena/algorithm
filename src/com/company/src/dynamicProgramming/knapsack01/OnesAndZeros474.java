package com.company.src.dynamicProgramming.knapsack01;

import java.util.Arrays;

/**
 * 474. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 *
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 *
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *
 * 链接：https://leetcode.cn/problems/ones-and-zeroes/
 */
public class OnesAndZeros474 {
    public static void main(String[] args) {
        OnesAndZeros474 ins = new OnesAndZeros474();
       // String[] s = {"11","11","0","0","10","1","1","0","11","1","0","111","11111000","0","11","000","1","1","0","00","1","101","001","000","0","00","0011","0","10000"};
        String[] s = {"10","0001","111001","1","0"};
        System.out.println(ins.findMaxForm2(s,5,3));
    }

    /**
     * 解法一：记忆化搜索
     * 两种情况，到index时，包含自身和不包含自身，看哪种大
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] memo = new int[strs.length][m+1][n+1];
        for (int i = 0; i <strs.length; i++) {
            for (int j = 0; j <=m ; j++) {
                for (int k = 0; k <=n ; k++) {
                    memo[i][j][k]=-1;
                }
            }
        }
        memo[0][0][0] = 0;
        return tryFind(strs, 0, m, n, memo);
    }
    
    public int tryFind(String[] strs, int index, int m, int n,int[][][] memo){

        int len = strs.length;
        if (index>=len){
            return 0;
        }
        if (memo[index][m][n] !=-1)
            return memo[index][m][n];
        int zero = calZero(strs[index]);
        int one = strs[index].length()-zero;
        int within = 0;
        if (zero<=m && one<=n) {
            within = 1+ tryFind(strs, index+1, m-zero, n-one, memo);
        }

        int without = tryFind(strs, index+1, m, n, memo);
        memo[index][m][n] =  Math.max(within,without);
        return Math.max(within,without);

    }
    public static int calZero(String s){
        int count = 0;
        for (int i = 0; i <s.length() ; i++) {
            char c = s.charAt(i);
            count += c=='0'?1:0;
        }
        return count;
    }

    /**
     * 动态规划。从[0，index]中，当m和n不同时，最多能有多少个元素
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[strs.length][m+1][n+1];
        for (int i = 0; i <strs.length; i++) {
            String str = strs[i];
            int zero = calZero(str);
            int one = str.length()-zero;
            // base条件
            if (i==0){
                for (int j = m; j >=zero ; j--) {
                    for (int k = n; k >=one ; k--) {
                        dp[i][j][k]=1;
                    }
                }
            }else {
                for (int j = 0; j <=m ; j++) {
                    for (int k = 0; k <=n ; k++) {
                        if (j<zero || k<one){ // j k比较小，说明不可能包含当前元素
                            dp[i][j][k] = dp[i-1][j][k];
                        }else {
                            int  within = 1 + dp[i-1][j-zero][k-one];
                            int without =  dp[i-1][j][k];
                            dp[i][j][k] = Math.max(within, without); // 包含或者不包含，选一个
                        }
                    }
                }
            }
        }
        return dp[len-1][m][n];
    }


    /**
     * 解法二：动态规划，空间优化版本
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm3(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <strs.length; i++) {
            String str = strs[i];
            int zero = calZero(str);
            int one = str.length()-zero;
            if (i==0){
                for (int j = m; j >=zero ; j--) {
                    for (int k = n; k >=one ; k--) {
                        dp[j][k]=1;
                    }
                }
            }else {
                for (int j = m; j >= zero ; j--) {
                    for (int k = n; k >= one ; k--) {
                        int  within = 1 + dp[j-zero][k-one];
                        int  without =  dp[j][k];
                        dp[j][k] = Math.max(within, without); // 包含或者不包含，选一个

                    }
                }
            }
        }
        return dp[m][n];
    }

}
