package com.company.src.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 * 链接：https://leetcode.cn/problems/triangle/
 */
public class Triangle120 {

    /**
     * 解法一：递归。先求出上一层的最小值的列表。在处理此层与上一层列表的和。
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> sumList =  getSumList(triangle,triangle.size()-1);
        int minSum = Integer.MAX_VALUE;
        for (int sum: sumList) {
            minSum = Math.min(minSum, sum);
        }
        return minSum;
    }

    public  List<Integer> getSumList(List<List<Integer>> triangle, int level) {
        List<Integer> result = new ArrayList<>();
        if (level==0) {  //最上层
            result.add(triangle.get(0).get(0));
            return result;
        }
        List<Integer> nums = triangle.get(level);
        List<Integer> sums = getSumList(triangle, level-1);

        // 每层最左边单独处理
        result.add(sums.get(0)+nums.get(0));
        //中间
        for (int i = 1; i < nums.size()-1 ; i++) {
            result.add(Math.min(sums.get(i-1), sums.get(i))+nums.get(i));
        }
        // 最右边
        result.add(sums.get(sums.size()-1)+nums.get(nums.size()-1));

        return result;
    }

    /**
     * 解法二：动态规划。自底向上，从第一层开始求每一层的结果。 保存在原地。（也可以新建一个n*n的矩阵，或者2个n长度的数组交替使用。）
     * @param triangle
     * @return
     */
    public static int minimumTotal2(List<List<Integer>> triangle) {

        if (triangle.size()==1){
            return triangle.get(0).get(0);
        }
        // 第一层不做处理

        // 后续层
        for (int i = 1; i <triangle.size() ; i++) {
            List<Integer> lastLevelSum =triangle.get(i-1);
            List<Integer> nums = triangle.get(i);

            // 每层最左边单独处理
            nums.set(0, lastLevelSum.get(0)+nums.get(0));
            //中间
            for (int j = 1; j < nums.size()-1 ; j++) {
                nums.set(j, Math.min(lastLevelSum.get(j-1), lastLevelSum.get(j)) + nums.get(j));
            }
            // 最右边
            nums.set(lastLevelSum.size(), lastLevelSum.get(lastLevelSum.size()-1)+nums.get(nums.size()-1));

        }

        // 从最后一层取出最小值
        int minSum = Integer.MAX_VALUE;
        for (int sum: triangle.get(triangle.size()-1)) {
            minSum = Math.min(minSum, sum);
        }

        return minSum;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle  = new ArrayList<>();
        //[[2],[3,4],[6,5,7],[4,1,8,3]]
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
        System.out.println(minimumTotal2(triangle));
    }

}

