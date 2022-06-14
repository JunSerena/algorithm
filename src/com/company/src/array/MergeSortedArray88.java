package com.company.src.array;

/**
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeSortedArray88 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0} ;
        int m = 3;
        int[] nums2 = {2,5,6} ;
        int n = 3;

        merge(nums1,m, nums2, n);

    }

    /**
     * 解法一：逆向遍历数组1和2，然后将最大值填充在数组1的末尾
     * @param nums1 nums1.length == m + n
     * @param m
     * @param nums2 nums2.length == n
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int k = nums1.length-1;
        int i=m-1;
        int j =n-1;
        while (i>=0 && j>=0){
            if (nums1[i]>nums2[j]){  //注意虽然 nums1[i] > nums2[j]，但是可能nums1[i-1]也比nums2[j]大
                nums1[k] =nums1[i];
                i--;
            }else {
                nums1[k]= nums2[j];
                j--;
            }

            k--;
        }

        while (j>=0){
            nums1[j]= nums2[j];
            j--;
        }
    }
}
