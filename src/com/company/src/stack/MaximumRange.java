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
        int[] src2 = {5,2,3,8,1};

        System.out.println(solution1(src));
        System.out.println(solution1(src2));
        System.out.println(solution2(src));
        System.out.println(solution2(src2));
    }
    /**
     * 暴力解法 O(n2)
     */
    private  static int solution1(int[] src){
        if (null==src || src.length==0)
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
     *
     * 两个要点：最小数，区间的和
     * - 区间和：利用前缀和数组（sum[i]代表 src[0]到src[i-1]的和）
     *          因此，下标[x,y]的区间和为
     *          src[0]+..+src[x-1]+src[x]+..+src[y] -(src[0]+..+src[x-1])
     *          = sum[y+1]-sum[x]
     *
     *          [x,y)的区间和为 sum[y]-sum[x]
     * - 最小数：利用stack
     *          遍历数组，src[i]作为最小值时，求出区间
     */
    private  static int solution2(int[] src){
        if (null==src || src.length == 0)
            return 0;
        int len = src.length;
        int[] sum = new int[len+1];
        sum[0] = 0;
        //前缀和数组
        for (int i=1; i<=len; i++){
            sum[i] =  sum[i-1] + src[i-1];
        }

        int max=0;
        //取最小值,栈内存放数组下标
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<len; i++){

            //找到栈顶元素的右边界i,且不包括i，因为src[i]比栈顶要小
            while (!stack.isEmpty() && src[i]<src[stack.peek()]){

                int index = stack.pop();
                int left;  //左边界，包含left
                int right = i;//右边界，不包含right

                //寻找左边界
                if (stack.isEmpty()){  //栈为空时，说明peek左边的元素都比src[peek]要大，即左边界可以延伸至数组初始位0
                    left=0;
                }else{
                    left=index;  //栈不为空时，说明下一个栈顶元素都比src[peek]要小，则src[peek]要作为最小值，左边界只能是自己。
                }

                //即，src[peek]元素为最小值的时候，区间为[left,right)
                max = Math.max(max,src[index]*(sum[right]-sum[left]));

            }
            stack.push(i);
        }

        //右边界封顶，但是栈内还存在元素，说明栈内元素都小于src[len-1]，故而右边界为len,不包含len。
        while (!stack.isEmpty()){
            int index = stack.pop();
            //寻找左边界
            int left = len;
            int right = len;
            if (stack.isEmpty()){
                left=0;
            }else{
                left=index;
            }

            max = Math.max(max,src[index]*(sum[right]-sum[left]));

        }

        return max;

    }
}
