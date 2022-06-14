package com.company.src.array;

/**
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 必须在不使用库的sort函数的情况下解决这个问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortColors75 {

    public static void main(String[] args) {
        int[] nums = {2,0,1};
        sortColors1(nums);
        for (int n:nums
             ) {
            System.out.println(n);
        }

    }

    /**
     * 解法一：分别统计0 1 2 出现的次数，然后重新赋值
     * @param nums
     */
    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i<count[0]){
                nums[i]=0;
            }else if (i < count[0]+count[1]){
                nums[i]=1;
            }else {
                nums[i]=2;
            }
        }
    }

    /**
     * 解法二：分段，[0,zero)上存0，(two, len)上存2，中间段[zero,i]存1
     * @param nums
     */
    public static void sortColors1(int[] nums) {
        int zero=0;
        int two=nums.length-1;
        for (int i = 0; i <= two ;) {
            if (nums[i]==1){
                i++;
            }else if (nums[i]==2){
                nums[i]=nums[two];
                nums[two]=2;
                two--;
            }else if (nums[i]==0){

                nums[i] = nums[zero];
                nums[zero] =0;
                zero++;
                i++;
            }
        }

    }

    //解法二改成闭区间。[0,zero] 上存0，[two, len-1]上存2
    public static void sortColors2(int[] nums) {
        int zero=-1; // nums[0... zero] =0，所以zero的初始值不能为0，因为不知道nums[0]是什么值
        int two=nums.length; // nums[two... len-1] =2, two初始值不能为len-1，因为刚开始无法确定nums[len-1]

        for (int i = 0; i < two ;) {  //two是闭区间，所以 = two 就停止了
            if (nums[i]==1){
                i++;
            }else if (nums[i]==2){
                two--;
                nums[i]=nums[two];
                nums[two]=2;

            }else if (nums[i]==0){
                zero++;
                nums[i] = nums[zero];
                nums[zero] =0;
                i++;
            }
        }

    }

}
