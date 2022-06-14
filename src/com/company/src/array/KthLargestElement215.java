package com.company.src.array;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */
public class KthLargestElement215 {

    public static void main(String[] args) {
        //int[] nums = {19,3,38,5,47,15,36,26,27,2,46,4,44,50,48};
        int[] nums = {2 ,1};
        int k =2;
        //quickSortExt(nums,0, nums.length-1, k);
        maxHeapSort(nums, k);
        for (int n: nums   ) {
            System.out.print(n + ", ");
        }
        System.out.println("第"+k+ "大为：" + nums[0]);

    }


    /**
     * 解法一：借用快排的思想: 快排的变种,不需要完全排序
     */
    public static void quickSortExt(int[] nums, int start, int end, int target){
        if (start>=end)
            return;
        int left = start, right = end;
        int key = nums[left]; //基准值

        while ( left < right) {
            while (left < right && nums[right]>=key){
                right--;
            }
            nums[left] = nums[right]; //小于基准值，放左边

            while (left < right && nums[left]<key){
                left++;
            }
            nums[right] = nums[left]; //大于等于基准值，放右边
        }
        nums[left] = key;

        if (target < nums.length-left){
            quickSortExt(nums, left+1, end, target);
        }else {
            quickSortExt(nums, start, left-1, target);
        }

    }

    /**
     * 解法二：借用堆排的思想1：构建K个节点的小顶堆. 然后将k后的节点依次于堆顶比较，替换，调整堆结构
     */
    public static void minHeapSort(int[] nums,  int k ){
        //先构建小顶堆
        for (int i = k/2-1; i >=0; i--) {
            adjustHeap(nums, i, k);
        }

        //遍历比较数组 k 后的值
        for (int i = k; i < nums.length; i++) {
            if (nums[i]>nums[0]){ //比堆顶大，替换，重新调整堆结构
                swap(nums, i, 0);
                adjustHeap(nums,0, k);
            }
        }

    }

    public static void adjustHeap(int[] arr, int index, int len){
        int min;
        for (int i = index; i <= len/2-1; ) {
            int right = 2*i+2;
            int left = right -1;
            if (right< len && arr[right] < arr[left]){ //右子节点存在且小于左子节点
                min = right;
            }else
                min = left;

            if (arr[i] > arr[min]){
                swap(arr, i, min); //交换以后去处理子树
                i = min;
            }else
                return;

        }
    }

    public static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /**
     * 解法3：利用堆排序的思想，构建n个节点的大顶堆，然后把最大的沉到数组末尾，调整大顶堆后，继续把堆顶沉到数组后
     *  直到沉到第K个元素
     *  即 不完整的大顶堆排序。
     */

    public static int maxHeapSort(int[] nums,  int k ){
        // 先构建大顶堆
        for (int i = nums.length/2-1; i >=0; i--) {
            adjustMaxHeap(nums, i, nums.length);
        }

        //踢出堆顶，放到数组末尾，循环K-1次，找到第K大元素，结束
        int limit = nums.length-k;
        for (int i = nums.length-1; i > limit ; i--) {
            swap(nums,0, i);
            adjustMaxHeap(nums,0, i);
        }
        return nums[0];
    }

    public static void adjustMaxHeap(int[] arr, int index, int len){
        int max;
        for (int i = index; i <= len/2-1; ) {
            int right = 2*i+2;
            int left = right -1;
            if (right< len && arr[right] > arr[left]){
                max = right;
            }else
                max = left;

            if (arr[i] < arr[max]){
                swap(arr, i, max); //交换以后去处理子树
                i = max;
            }else
                return;

        }
    }
}

