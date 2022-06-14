package com.company.src.array;

/**
 * 快速排序：
 * 动画演示：https://www.bilibili.com/video/BV1cy4y187KT/
 * 基本思想：
 * 1、先从数列中取出一个元素作为基准值
 * 2、扫描数列，将比基准数小的都放到它的左边，大于等于基准数的元素都要放到它的右边，得到左右两个区间
 * 3、再对区间重复第二步，直到各个区间小于两个元素
 *
 * 其实就是挖坑填数+分治思想
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {19,3,38,5,47,15,36,26,27,2,46,4,44,50,48};
        //int[] nums = {23,1,34,12};
        sort(nums,0, nums.length-1);
        for (int n: nums   ) {
            System.out.print(n + ", ");
        }
    }


    public static void sort(int[] nums, int left, int right){

        if (left>=right)
            return;

        int originLeft =left, originRight= right;

        // 取基准值，可以取最左边的值（相当于挖了个坑）
        int key = nums[left];
        while (left < right) {
            //先从右边开始，找比基准值要小的，填到左边的坑里。（先从右边找是因为左边有个坑，可以填数）
            while (left<right && nums[right]>=key){
                right--;
            }
            nums[left] =nums[right];//找到以后，填到左边

            //再从左边找，找到大于等于基准值的，填到右边
            while (left<right && nums[left]<key){
                left++;
            }
            nums[right] =nums[left]; //找到以后，填到右边
        }

        // left和right相遇以后，说明左边都比基准值小，右边都比基准值大或者等了，所以这个点就是基准值的下标
        nums[left]= key;


        //分治，递归调用排序，把左边和右边两个区间都排好序
        sort(nums, originLeft, left-1);
        sort(nums, left+1, originRight);

    }
}
