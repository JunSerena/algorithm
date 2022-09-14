package com.company.src.dynamicProgramming;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.Map;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 *
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 *
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 *
 * 题目数据保证答案肯定是一个 32 位 的整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 *
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 *
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 *
 * 链接：https://leetcode.cn/problems/decode-ways/
 */
public class DecodeWays91 {

    /**
     *  解法一，递归：两种情况，取一个数字（一个数字需不为0）或者两个数字（大于等于10小于等于26）组合
     *  超时了，但是结果是对的！
     */
    public static int numDecodings(String s) {
        if (s.startsWith("0"))
            return 0;
        int len = s.length();
        if (len==1){
            return 1;
        }
        if (len==2 ){
            if (Integer.parseInt(s)<=26)
                return 1+numDecodings(s.substring(1));
            else
                return numDecodings(s.substring(1));
        }
        //选择一：单个元素只要排除0，都符合
        int count = numDecodings(s.substring(1,len));
        // 选择二：两个元素，需要满足不以0开头，小于26
        if (Integer.parseInt(s.substring(0,2))<=26)
            count += numDecodings(s.substring(2,len));
        return count;
    }


    /**
     * 解法二：记忆化搜索，优化递归
     * @param s
     * @return
     */
    Map<String, Integer> map = new HashMap<>();
    public int numDecodings2(String s) {
        if (s.startsWith("0"))
            return 0;
        int len = s.length();
        if (len==1){
            return 1;
        }
        if (len==2 ){
            if (Integer.parseInt(s)<=26)
                return 1+numDecodings(s.substring(1));
            else
                return numDecodings(s.substring(1));
        }
        //选择一：单个元素只要排除0，都符合

        String s1 = s.substring(1,len);
        if (!map.containsKey(s1)){
            map.put(s.substring(1,len), numDecodings(s1));
        }
        int count = map.get(s1);

        // 选择二：两个元素，需要满足不以0开头，小于26
        String tmp = s.substring(0,2);
        if (Integer.parseInt(tmp)<=26){
            String s2 = s.substring(2, len);
            if (!map.containsKey(s2)){
                map.put(s2,numDecodings(s2));
            }
            count += map.get(s2);
        }

        return count;
    }

    /**
     * 解法三：动态规划，从末尾开始，逐步往前遍历。使用map保存已知子串的方法。
     * ps:好像不是标准动态规划。
     * @param s
     * @return
     */
    public int numDecodings3(String s) {
        int len = s.length();
        Map<String, Integer> map = new HashMap<>();
        if (len==1)
            return Integer.parseInt(s)!=0? 1:0;

        String s1 = s.substring(len-1);
        if (Integer.parseInt(s1)>0)
            map.put(s1, 1);
        else
            map.put(s1, 0);

        String s2 = s.substring(len-2);
        if (Integer.parseInt(s2)<10)
            map.put(s2, 0);
        else if ( Integer.parseInt(s2) >26)
            map.put(s2, map.get(s2.substring(1)));
        else{
            map.put(s2, 1+map.get(s2.substring(1)));
        }


        for (int i = len-3; i >=0; i--) {
            String cur = s.substring(i);
            int c1 = Integer.parseInt(cur.substring(0,1));
            int c2 = Integer.parseInt(cur.substring(0,2));


            if (c1==0)
                map.put(cur, 0);
            else if (c2>26)
                map.put(cur, map.get(cur.substring(1)));
            else{
                map.put(cur, map.get(cur.substring(1))+map.get(cur.substring(2)));
            }

        }
        return map.get(s);
    }


    public static void main(String[] args) {
        DecodeWays91 decodeWays = new DecodeWays91();

        System.out.println(decodeWays.numDecodings3("111111111111111111111111111111111111111111111"));
        System.out.println(decodeWays.numDecodings4("111111111111111111111111111111111111111111111"));

        //1836311903
        //System.out.println(numDecodings("111111111111111111111111111111111111111111111"));
        // System.out.println(decodeWays.numDecodings2("111111111111111111111111111111111111111111111"));
    }


    /**
     * 动态规划，转移方程有两个。
     * @param s
     * @return
     */
    public int numDecodings4(String s) {
        if (s.startsWith("0")){
            return 0;
        }
        int len = s.length();

        int[] dp = new int[len+1];
        dp[0] =1; //空串
        dp[1] = 1; //第一个字符串
        for (int i = 1; i <len ; i++) {
            int count = 0;
            int a = s.charAt(i)-'0';
            int b = s.charAt(i-1)-'0';
            // 转移方程一：取一个值, fn= fn-1 只有不为0，才能解码
            if (a!=0){
                count += dp[i];
            }
            //转移方程二：取两个值，fn = fn-2，第一个值不为0，且和小于26才能解码
            int val = b*10+a;
            if (val>=10 && val<=26){
                count += dp[i-1];
            }

            // 两种情况相加，复制
            dp[i+1] = count;
        }

        return dp[len];
    }
}
