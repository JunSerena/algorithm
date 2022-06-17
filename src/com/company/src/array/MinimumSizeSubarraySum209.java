package com.company.src.array;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumSizeSubarraySum209 {
    public static void main(String[] args) {
        int[] nums = {1,4};
        int target = 4;
        System.out.println(minSubArrayLen2(target, nums));

    }

    //暴力解法
    public int minSubArrayLen(int target, int[] nums) {

        int dif = Integer.MAX_VALUE;
        for (int i = 0; i <nums.length ; i++) {
            int sum=0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum>=target){
                    dif = Math.min(dif, j-i+1);
                }
            }
        }
        return dif==Integer.MAX_VALUE ? 0 : dif;
    }


    /**
     * 滑动窗口解法：
     * fast指针滑动，直至找到一个和大于等于target的窗口。此时,slow指针向前滑动，到sum小于target，fast指针再次滑动。构成一个滑动窗口
     * 此滑动窗口的sum大于等于target，找到fast-slow最小的值。
     */
    public static int minSubArrayLen2(int target, int[] nums) {
        int dif = Integer.MAX_VALUE;

        int slow = 0, fast =0, sum = 0; // [slow, fast）存
        while (slow<=fast && fast<=nums.length){  //fast可以等于length，是因为他是开区间。但是当fast已经到末尾时，只能在sum大于target的情况下移动slow指针。
            if (sum>=target){
                dif = Math.min(dif, fast-slow);
                sum -= nums[slow];
                slow++;
            }else {
                if (fast==nums.length){ // 当fast指针移动到最右边了的时候，sum还是小于target，说明slow不用再往前移动了，不可能有sum小于target了。
                    break;
                }
                sum += nums[fast];
                fast++;
            }
        }

        return dif==Integer.MAX_VALUE ? 0 : dif;
    }

    // 解法二换种写法
    public static int minSubArrayLen3(int target, int[] nums) {
        int dif = Integer.MAX_VALUE;

        int slow = 0, fast =-1, sum = 0; // [slow, fast] 为滑动窗口
        while (slow < nums.length){
            if (sum < target ){
                if ( fast+1 <nums.length){
                    fast++;
                    sum += nums[fast];
                }else
                    break;
            }else {
                dif = Math.min(dif, fast-slow+1);
                sum -= nums[slow];
                slow++;
            }
        }

        return dif==Integer.MAX_VALUE ? 0 : dif;
    }

}
