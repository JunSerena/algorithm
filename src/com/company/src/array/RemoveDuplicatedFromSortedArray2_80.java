package com.company.src.array;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 */
public class RemoveDuplicatedFromSortedArray2_80 {

    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int k = 1;
        int count =1;  //count记录当前nums[k-1]的值出现次数
        for (int i=1; i<len;i++){
            if (nums[k-1]!=nums[i]) {
                nums[k] = nums[i];
                k++;
                count=1;
            }else {
                if (count==1){
                    nums[k] = nums[i];
                    k++;
                    count=0;
                }
            }
        }
        return k;
    }



    public int solve(int[] nums) {

        int k =1;
        for (int i = 2; i <nums.length ; i++) {

            if (nums[k]!=nums[i]){  // nums[i]是新值，可以之直接放到k+1的下标处
                nums[k+1]=nums[i];
                k++;
            }else{ // nums[i]是旧值，已经出现过起码一次
                if (nums[k-1]!=nums[k]){ // nums[k-1]和nums[k]不相同，说明只出现了一次，可以把nums[i]再放入k+1处。
                    nums[k+1]=nums[i];
                    k++;
                }
            }

        }
        return k+1;
    }


    // 官方解答：
    // 本题要求相同元素最多出现两次而非一次，所以我们需要检查上上个应该被保留的元素 nums[slow−2] 是否和当前待检查元素nums[fast] 相同。
    // 当且仅当 nums[slow−2]=nums[fast] 时，当前待检查元素 nums[fast] 不应该被保留（因为此时必然有 nums[slow−2]=nums[slow−1]=nums[fast]）。
    // 最后 slow 即为处理好的数组的长度。
    //
    public int removeDuplicates2(int[] nums) {
        int len = nums.length;
        if (len<2){
            return len;
        }

        int slow =2, fast=2;
        while (fast<len){
            if (nums[slow-2]!=nums[fast]){
                nums[slow]=nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
