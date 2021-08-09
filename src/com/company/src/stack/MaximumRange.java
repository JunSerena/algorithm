package com.company.src.stack;

import java.util.Stack;

/**
 * 区间最大和：数组任意连续区间内最小值与区间和的乘积，取乘积的最大值
 * 如[6,2,1]
 * 解：可分为区间
 * [6] = 6*6 = 36,
 * [2] = 2*2 = 4,
 * [1] = 1*1 =1,
 * [6,2] = 2 *(6+2) =16,
 * [2,1] = 1*3 = 3,
 * [6,2,1] = 1 * 9 = 9
 *
 * 所以最大值是区间在[6]，结果为36
 */
public class MaximumRange {
    public static void main(String[] args){
        int[] src = {6,2,1};
        int[] src2 = {5,2,3,4,1};

        System.out.println(solution1(src));
        System.out.println(solution1(src2));
    }
    /**
     * 暴力解法 O(n2)
     */
    private  static int solution1(int[] src){
        if (null==src)
            return 0;
        int len = src.length;
        //数组下标[i,j]区间,取[i,j]区间内src[j]最小值，以及src[i]到src[j]的和
        int max =0;
        for (int i=0; i<len; i++){
            int min =src[i];
            int sum =0;
            for (int j=i;j<len;j++){
                if (j==i){
                    sum = min;
                }else {
                    min = Math.min(min, src[j]);
                    sum += src[j];
                }
                max = Math.max(min*sum, max);
            }
        }
        return max;
    }

    /**
     * 解法O(n)：src[i]作为最小值时，找出其区间，求区间和，之后求乘积，最后比较乘积的最大值
     * 两个要点：最小数，区间的和
     * - 区间和：利用前缀和数组（sum[i]代表 src[0]到src[i]的和）
     *          因此，下标[x,y]的区间和为
     *          src[0]+..+src[x-1]+src[x]+..+src[y] -(src[0]+..+src[x-1])
     *          = sum[y]-sum[x-1]
     * - 最小数：利用stack
     *          遍历数组，src[i]作为最小值时，求出区间
     */
    private  static int solution2(int[] src){
        if (null==src)
            return 0;
        int len = src.length;
        int[] sum = new int[len];
        sum[0] = 0;
        //前缀和数组
        for (int i=0; i<len; i++){
            sum[i] += src[i];
        }

        //取最小值,栈内存放数组下标
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<len; i++){


        }


    }
}
