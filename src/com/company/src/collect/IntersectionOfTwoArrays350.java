package com.company.src.collect;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 350. 两个数组的交集 II
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 * 链接：https://leetcode.cn/problems/intersection-of-two-arrays-ii/
 */
public class IntersectionOfTwoArrays350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i,map.getOrDefault(i,0)+1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (!map.containsKey(i)){
                continue;
            }
            list.add(i);
            if (map.get(i)==1){
                map.remove(i);
            }else {
                map.put(i, map.get(i)-1);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i <list.size() ; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
