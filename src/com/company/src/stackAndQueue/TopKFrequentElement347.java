package com.company.src.stackAndQueue;


import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 *
 */
public class TopKFrequentElement347 {

    /**
     * 解法一：统计排序
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        //统计出现的频率
        for (int num: nums ) {
            map.put(num, map.getOrDefault(num,0)+1);
        }

        //排序
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (a, b)->map.get(b)- map.get(a));
        
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i]=list.get(i);
        }

        return result;
    }


    /**
     * 解法二：利用优先队列，维护一个大小为k的优先级队列
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        //统计出现的频率
        for (int num: nums ) {
            map.put(num, map.getOrDefault(num,0)+1);
        }

        //维护大小为k的优先队列
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (a, b)->map.get(b)-map.get(a)); //大顶堆

        queue.addAll(map.keySet());

        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i]=queue.poll();
        }

        return result;

    }
}
