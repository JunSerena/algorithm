package com.company.src.array;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 链接：https://leetcode-cn.com/problems/move-zeroes/
 */
public class MoveZeros283 {

    /**
     * 思路一：空间换时间，new一个全新的数组，将所有非0数组保存，最后补0(题目不允许，只能在原数组上操作)
     *
     * @param nums
     */
    public void moveZeroes1(int[] nums) {
        if (null == nums || nums.length ==0)
            return;
        int len = nums.length;
        int[] copy = new int[len];
        int j =0;
        for (int i=0; i<len;i++){
            if (nums[i]!=0){
                copy[j] = nums[i];
                j++;
            }
        }

        for (int i=0; i<len; i++){
            if (i<=j){
                nums[i] = copy[i];
            }else
                nums[i] = 0;
        }

    }
    /**
     * 思路二：两个指针，i指向第一个0值，j指向i之后的第一个非0值，i和j处的值作交换，i+1,j+1往后走
     *
     * 反思：这一处想法和思路四是类似的，但是没有想清楚循环主变量和循环不变量的定义到底是哪个（到底是遍历i还是遍历j）。
     * 导致代码在写if语句时纠结与i与j之间的大小关系。
     * 此处i等同于思路四的k,j等同于思路四的循环主变量i
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        if (null == nums || nums.length ==0)
            return;
        int len = nums.length;
        int i=0;
        for (int j=0; j<len; j++){    //理清楚变量的定义之后，相互关系也清晰了。代码一改就跟思路四一样了。
            if (nums[j]!=0){
                //交换
//                int temp = nums[i];
//                nums[i]= nums[j];
//                nums[j]= temp;
//                i++;
                //此处再次优化，情况为数组全为非0时
                if (j!=i){
                    int temp = nums[i];
                    nums[i]= nums[j];
                    nums[j]= temp;
                }
                i++;
                //j++;  for循环中自增
            }
        }
//        while (j<len && i<len){
//            if (nums[i]!=0){
//                i++;
//                if (j<i){
//                    j=i;
//                }
//            }else if (nums[j]==0 && j>=i){
//                j++;
//            }else {
//                //交换
//                int temp = nums[i];
//                nums[i]= nums[j];
//                nums[j]= temp;
//                i++;
//                j++;
//            }
//        }
    }

    /**
     * 思路三：思路一的优化，在原数组本身改。
     * 遍历数组，循环量为i，维持变量k（循环不变量）, 确保[0,k)的元素均为非0元素，[k,len)全为0元素
     * 遍历过程中直接将i元素赋值给k元素。
     */
    public void moveZeroes3(int[] nums) {
        if (null == nums || nums.length ==0)
            return;
        int len = nums.length;
        int k=0;
        for (int i =0; i<len; i++){
            if (nums[i]!=0){
                nums[k]=nums[i];
                k++;
            }
        }
        for (int i=k; i<len;i++){
            nums[i]=0;
        }
    }

    /**
     * 思路四：思路三的优化，不进行一一赋值，而是直接交换，其实和思路二是一样的，但是思路二写的代码不好看,循环变量没有确定清楚。
     * 遍历数组，循环量为i，维持变量k（循环不变量）, 确保[0,k)的元素均为非0元素，[k,len)全为0元素
     * 遍历过程中直接将i元素 和 k元素 进行交换。
     */
    public void moveZeroes4(int[] nums) {
        if (null == nums || nums.length ==0)
            return;
        int len = nums.length;
        int k=0;
        for (int i =0; i<len; i++){
            if (nums[i]!=0){
                if (i!=k){
                    nums[k]=nums[i];  //这一步做交换
                    nums[i]=0;
                }
                k++;
            }
        }
    }
}
