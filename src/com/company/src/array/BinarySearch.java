package com.company.src.array;

/**
 * 二分查找法
 *
 * 注意边界问题: 想清楚边界变量的定义是什么
 *
 * 重点理解：循环不变量, 在[left,right]中寻找target元素，闭区间。
 *          left和right的值虽然是变化的，但是left和right的本质含义是不变的。
 */
public class BinarySearch {
    //二分查找，返回目标元素在数组中的下标
    static int binarySearch(int arr[], int n, int target){
        int left=0, right = n-1;  //在[left,right]中寻找target元素
        while (left<=right){      // 当left==right时，区间[left,right]仍然有效。
            int mid = (left+right)/2;
            if (target == arr[mid]){
                return mid;
            }else if (target < arr[mid]){
                right = mid-1;
            }else {
                left = mid +1;
            }

        }
        return -1;
    }

    public static void main(String[] args){
        int n =1000000;
        int[] arr = new int[n];
        for (int i=0; i<n;i++){
            arr[i]=i;
        }
        long startTime=System.currentTimeMillis();   //获取开始时间
        for (int i=0; i<n;i++){
            binarySearch(arr,n, i);
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }
}
