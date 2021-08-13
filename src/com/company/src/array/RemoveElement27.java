package com.company.src.array;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。不需要考虑数组中超出新长度后面的元素。
 *
 * 链接：https://leetcode-cn.com/problems/remove-element
 */
public class RemoveElement27 {

    /**
     * 解法一：头尾指针，一个从头开始，一个从尾开始，遇见nums[i]为目标值，而nums[j]不是，则做交换。直到两指针相遇
     *
     * [0,i)为非val值，故而应返回i。
     * (j,len)为val值
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (null == nums || nums.length==0)
            return 0;
        int len = nums.length;
        int i, j;
        for ( i=0, j=len-1; i<=j;  ){
            if (nums[i]==val && nums[j]!=val ){
                //可以不必交换，不关系[k,len)的值
                //int temp = nums[i];
                nums[i] = nums[j];
                //nums[j] = temp;
                i++;
                j--;
            }else if (nums[i]==val && nums[j]==val){
                j--;
            }else if(nums[i]!=val && nums[j]==val){
                i++;
                j--;
            }else {
                i++;
            }

        }
        return i;
    }


    /**
     * 思路二：类似283题的moveZeros。
     * [0,k)保存非val值，为循环不变量。i作为循环的主要变量，遍历数组。遇见非val值就与nums[k]交换，k++.
     * @param nums
     * @param val
     * @return
     */
    public int removeElement2(int[] nums, int val) {
        if (null == nums || nums.length==0)
            return 0;
        int len = nums.length;
        int k =0;
        for (int i =0; i<len; i++){
            if (nums[i]!=val){
                if (i!=k){
                    int temp = nums[i];
                    nums[i] = nums[k];
                    nums[k] = temp;
                }
                k++;
            }
        }
        return k;
    }

    /**
     * 思路三：二的优化，题目给出了，不关心数组k后面的值是什么，因此不必要做交换，直接将nums[i]赋值给nums[k]。
     * [0,k)保存非val值，为循环不变量。i作为循环的主要变量，遍历数组。遇见非val值就赋值给nums[k]，k++.
     * @param nums
     * @param val
     * @return
     */
    public int removeElement3(int[] nums, int val) {
        int k=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val)
                nums[k++]=nums[i];
        }
        return k;
    }


}
