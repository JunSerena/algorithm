package com.company.src.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原 IP 地址
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 * 链接：https://leetcode.cn/problems/restore-ip-addresses/
 */
public class RestoreIPAddresses93 {

    public static void main(String[] args) {

        RestoreIPAddresses93 ins= new RestoreIPAddresses93();
        List<String> res = ins.restoreIpAddresses("25525511135");
        System.out.println(res);
    }

    /**
     * 解法一：回溯
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        if (s==null || s.length()<4 || s.length()>12){
            return new ArrayList<>();
        }
        dfs(s,0,"",0);
        return result;
    }

    List<String> result = new ArrayList<>();


    /**
     * @param s
     * @param start：下一位索引
     * @param ans ： 当前已拼接的ip段
     * @param part：ip段数，总共4段。
     * @return
     */
    public boolean dfs(String s, int start, String ans, int part) {
        if (part==4){
            if (start == s.length())
                result.add(ans);
            return false;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = start; i < start+3 && i<s.length(); i++) {
            StringBuilder ansBuilder = new StringBuilder(ans);
            sb.append(s.charAt(i));
            String x = sb.toString();
            if (!validateIpPart(x))
                return false;
            ansBuilder.append(x).append(part == 3 ? "" : ".");
            dfs(s, i+1, ansBuilder.toString(), part+1);
        }
        return true;
    }

    public boolean validateIpPart(String x){
        if (Integer.parseInt(x)>255 || Integer.parseInt(x)<0)
            return false;
        return !x.startsWith("0") || x.length() == 1; //0开头的话，长度只能为1，不能有012这种。
    }

}
