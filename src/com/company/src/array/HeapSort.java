package com.company.src.array;
/**
 * 堆排序：https://www.cnblogs.com/chengxiao/p/6129630.html
 * - 大根堆：父节点的值大于或等于子节点的值
 *
 * 一般都用数组来表示堆，i结点的父结点下标就为(i–1)/2。
 * 它的左右子结点下标分别为2 * i + 1和2 * i + 2。如第0个结点左右子结点下标分别为1和2。
 *
 * 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 *
 * 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 *
 *
 * 堆排序的基本思路：
 *
 * 　　a.将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
 *      从最后一个非叶子结点开始（叶结点自然不用调整，第一个非叶子结点 arr.length/2-1），从左至右，从下至上进行调整。
 * 　　b.将堆顶元素与末尾元素交换，将最大/小元素"沉"到数组末端;
 * 　　c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
 *
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] nums = {4,6,8,5,9};
        //int k =1;
       // int res = findKthLargest(nums, k);
        buildHeap(nums);
        for (int n: nums   ) {
            System.out.print(n + ", ");
        }
      //  System.out.println("第"+k+ "大为：" + res);
    }


    public static void buildHeap(int[] arr){
        //1、从非叶子节点开始，构建小顶堆
        for (int i = arr.length/2-1; i >=0 ; i--) {
            adjustHeap(arr, i, arr.length);
        }


        for (int i = arr.length-1; i > 0 ; i--) {
            //2、将堆顶元素放到数组的末尾
            swap(arr,0, i);
            //3、重新调整小顶堆
            adjustHeap(arr,0, i);
        }


    }

    //调整以index为根的子树，向下调整，直到符合小顶堆的特性
    public static void adjustHeap(int[] arr, int index, int len){
        //调整非叶子节点
        for (int i = index; i <= len/2-1; ) {
            //比较左右子节点哪个更小，更小的和父节点比较
            int min ;
            if (2*i+2<len && arr[2*i+2] < arr[2*i+1]){ //存在右节点,且比左节点小
                min = 2*i+2;
            }else
                min = 2*i+1;

            if (arr[i] > arr[min]){ //父节点更大，就交换
                swap(arr, i, min);
                i = min; //交换以后，到子树上去，再重复以上步骤比较一遍
            }else
                break;
        }
    }

    public static void swap(int[] source, int a, int b){
        int temp=source[a];
        source[a] = source[b];
        source[b] = temp;
    }

}
