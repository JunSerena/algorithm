package com.company.src.collect;

import java.util.HashMap;
import java.util.List;

/**
 * 447. 回旋镖的数量
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的欧式距离相等（需要考虑元组的顺序）。
 *
 * 返回平面上所有回旋镖的数量。
 *
 *
 * 示例 1：
 *
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 *
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 示例 3：
 *
 * 输入：points = [[1,1]]
 * 输出：0
 *
 * 链接：https://leetcode.cn/problems/number-of-boomerangs/
 */
public class NumberOfBoomerangs447 {
    public static void main(String[] args) {
        // [[0,0],[1,0],[-1,0],[0,1],[0,-1]]
        int[][] points = new int[5][2];
        points[0]= new int[]{0, 0};
        points[1]= new int[]{1, 0};
        points[2]= new int[]{-1, 0};
        points[3]= new int[]{0,1};
        points[4]= new int[]{0, -1};

        System.out.println(numberOfBoomerangs(points));

    }

    public static int numberOfBoomerangs(int[][] points) {

        int count=0;

        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {

                if (j==i){  //不能是同一个点
                    continue;
                }
                int[] p1 =points[i];
                int[] p2 =points[j];

                /*
                 * cbrt(x)开立方
                 * sqrt(x)开平方
                 * hypot(x,y)求sqrt(x*x+y*y)在求两点间距离时有用sqrt((x1-x2)^2+(y1-y2)^2)
                 */
                //double dist = Math.sqrt((p1[0]-p2[0])*(p1[0]-p2[0]) + (p1[1]-p2[1])*(p1[1]-p2[1]));
                int dist = (p1[0]-p2[0])*(p1[0]-p2[0]) + (p1[1]-p2[1])*(p1[1]-p2[1]);  //用距离的平方做key
                map.put(dist,map.getOrDefault(dist,0)+1);
            }
            // 遍历map
            for (int dist : map.keySet()) {
                int num = map.get(dist);
                if (num>1){ //只有一个元素，无法成对
                    count += num*(num-1);  // 排列组合公式，从n个元素中取2个。
                }
            }
        }

        return count;
    }
}
