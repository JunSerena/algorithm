package com.company.src.greddy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. 无重叠区间
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
public class NonOverlappingIntervals435 {
    /**
     * 解法一：借用最长上升子序列的解法。On2，会超时
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        // 需要先对元素排序，以起始位置排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });


        // 借用最长上升子序列的解法
        int len = intervals.length;
        int[] dp = new int[len];
        Arrays.fill(dp,1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <i; j++) {
                if (intervals[i][0]>=intervals[j][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int max =1;
        for (int i = 0; i <len ; i++) {
            max = Math.max(max, dp[i]);
        }
        return len-max;
    }


    /**
     * 贪心算法，以结尾位置排序，选择最小的结尾位置.后续第一个起始位置比其大的就一定是最符合的下一个不重叠区间
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        // 需要先对元素排序，以结束位置排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                if (interval1[1]!=interval2[1])
                    return interval1[1] - interval2[1];
                return interval1[0] - interval2[0];
            }
        });

        int count=1, i=1;
        int len = intervals.length;
        int[] last = intervals[0];
        while (i<len){
            int[] cur = intervals[i];
            if (cur[0]>= last[1]){
                count++;

                last = cur;
            }
            i++;
        }
        return len-count;
    }

}
