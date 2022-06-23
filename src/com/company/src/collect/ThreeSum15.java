package com.company.src.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]      //两个-1就当作一个-1了。。。。。
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 * 链接：https://leetcode.cn/problems/3sum/
 */
public class ThreeSum15 {
    public static void main(String[] args) {
        System.out.println(threeSum3(new int[]{0, -1, -1, 2, -1, 0, 0}));

    }

    // 查找表解法：需要靠set去重复解，而且去除重复之前，需要按大小排序
    public static List<List<Integer>> threeSum(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();

        int len = nums.length;
        if (len<3){
            return result;
        }
        map.put(nums[0],0);
        for (int i = 1; i < len; i++) {
            int target = -nums[i];
            for (int j = 0; j < i; j++) {
                int a = nums[j];
                int b = target-a;
                if (map.get(b)!=null && map.get(b)!=j){
                    List<Integer> list = Arrays.asList(nums[i], a, b);
                    list.sort(Comparator.comparingInt(x -> x));
                    set.add(list);
                }
            }
            map.put(nums[i],i);
        }

        // 将set转为list
        result = new ArrayList<>(set);
        return result;

    }

    // 解法二：先排序，再双指针。用set去重。
    public static List<List<Integer>> threeSum2(int[] nums) {

        Set<List<Integer>> set = new HashSet<>();
        int len = nums.length;
        if (len<3){
            return new ArrayList<>();
        }

        Arrays.sort(nums); //先排序

        for (int i = 0; i <len && nums[i]<=0 ; i++) {
            // 当nums[i]固定的时候， 移动左右边界，使得左右边界之和等于0-nums[i]
            int left = i+1;
            int right = len-1;

            while (left< right) {
                if (nums[left]+nums[right]== -nums[i]){
                    set.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    right--;
                }else if (nums[left]+nums[right] < -nums[i]){
                    left++;
                }else {
                    right --;
                }
            }

        }
        return new ArrayList<>(set);

    }

    // 解法三：排序+双指针。不用set去重，直接在双指针循环的时候去重。
    public static List<List<Integer>> threeSum3(int[] nums) {

        List<List<Integer>> list = new ArrayList<>();
        int len = nums.length;
        if (len<3){
            return list;
        }

        Arrays.sort(nums); //先排序

        for (int i = 0; i <len && nums[i]<=0 ; i++) {

            if (i>0 && nums[i] == nums[i-1]){ //对最小值去重
                continue;
            }

            // 当nums[i]固定的时候， 移动左右边界，使得左右边界之和等于0-nums[i]
            int left = i+1;
            int right = len-1;

            while (left< right) {
                if (nums[left]+nums[right]== -nums[i]){
                    list.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    right--;
                    while (left<right && nums[left-1]==nums[left]){  //对中间值去重
                        left++;
                    }
                    while (left<right && nums[right+1]==nums[right]){ //对最大值去重
                        right--;
                    }

                }else if (nums[left]+nums[right] < -nums[i]){
                    left++;
                }else {
                    right --;
                }
            }

        }
        return list;

    }

    // 以前写的解法三。好像更简洁一些
    public List<List<Integer>> threeSum4(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(len < 3) return ans;

        Arrays.sort(nums); // 排序

        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else  R--;
            }
        }
        return ans;
    }

}
