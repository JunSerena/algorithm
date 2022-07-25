package com.company.src.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. 二进制手表
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 *
 * 例如，下面的二进制手表读取 "3:25" 。
 *
 *
 * （图源：WikiMedia - Binary clock samui moon.jpg ，许可协议：Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0) ）
 *
 * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
 *
 * 小时不会以零开头：
 *
 * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
 * 分钟必须由两位数组成，可能会以零开头：
 *
 * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 *
 *
 * 示例 1：
 *
 * 输入：turnedOn = 1
 * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * 示例 2：
 *
 * 输入：turnedOn = 9
 * 输出：[]
 *
 * 链接：https://leetcode.cn/problems/binary-watch/
 */
public class BinaryWatch401 {

    public static void main(String[] args) {
        BinaryWatch401 binaryWatch = new BinaryWatch401();
        System.out.println(binaryWatch.readBinaryWatch(9));
    }

    /**
     * 组合问题，从n个元素中抽取k个元素。
     * @param turnedOn
     * @return
     */
    public List<String> readBinaryWatch(int turnedOn) {

        if (turnedOn>10 || turnedOn<0)
            return ret;

        for (int i = 0; i <= turnedOn && i<=4 ; i++) {
            int min = turnedOn-i;

            if (  min>=0 && min<=6){
                List<Integer> hours = new ArrayList<>();
                List<Integer> minutes = new ArrayList<>();

                getTime(i, 4, 0, 0, hours);
                getTime(min, 6, 0, 0, minutes);

                combine(hours, minutes);
            }
        }

        return ret;

    }

    List<String>  ret = new ArrayList<>();


    public void getTime(int k, int len, int index, int sum, List<Integer> ans){
        if (k==0){
            if ( (len==4 && sum<12) || (len==6&&sum<60)){ //小时和分。sum有可能超过12和60。
                ans.add(sum);
            }
            return;
        }

        // [i,len)需要取至少k个元素， len-i>=k
        for (int i = index; i <= len-k ; i++) {
            int x = (int)Math.pow(2,i); // 1 2 4 8 16 32
            sum += x;
            getTime(k-1, len, i+1, sum, ans);
            sum -= x;
        }
    }


    // 组合时间
    public void combine(List<Integer> hours, List<Integer> minutes){
        if (hours==null && minutes==null)
            return;
        else if (hours==null){
            for (int min: minutes) {
                if (min==0){
                    ret.add("0:00");
                }else if (min<10){
                    ret.add("0:0"+min);
                }else
                    ret.add("0:"+min);
            }
        }else if (minutes==null){
            for (int hour: hours) {
                ret.add(hour+":00");
            }
        }else {
            for (int hour: hours) {
                for (int min: minutes) {
                    if (min==0){
                        ret.add(hour+":00");
                    }else if (min<10){
                        ret.add(hour+":0"+min);
                    }else
                        ret.add(hour+":"+min);
                }
            }
        }
    }

}
