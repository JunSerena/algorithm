package com.company.src.collect;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」 定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/happy-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HappyNumber202 {

    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }

    //利用hash集合找出有无环。
    public static boolean isHappy(int n) {

        Set<Integer> set = new HashSet<>();
        int sum =0;
        while ( sum!=1 && !set.contains(sum)){

            set.add(sum);

            //sum = getSum(n);
            sum = getNext(n);

            n=sum;
        }
        return sum==1;

    }



    //解法二：利用快慢指针找是否有环：
    // 弗洛伊德循环查找算法。这个算法是两个奔跑选手，一个跑的快，一个跑得慢。在龟兔赛跑的寓言中，跑的慢的称为 “乌龟”，跑得快的称为 “兔子”。
    //不管乌龟和兔子在循环中从哪里开始，它们最终都会相遇。这是因为兔子每走一步就向乌龟靠近一个节点（在它们的移动方向上）。
    //
    public static boolean isHappy2(int n) {
        int slow=n, fast=getNext(n);
        while (fast!=1 && slow!=fast){
            slow = getNext(slow);
            fast= getNext(fast);
            fast = getNext(fast);
        }
        return fast==1;
    }



    public static int getNext(int n){
        int sum = 0, base=10;
        while (n>0){
            int m = n % base;
            sum += m*m;
            n=n/base;
        }
        return sum;
    }

//    public static int getSum(int n){
//        String num = n+"";
//        int len = num.length();
//        int sum = 0;
//        for (int i = 0; i < len; i++) {
//            System.out.println((num.charAt(i)-'0'));
//            sum +=  (num.charAt(i)-'0') * ( num.charAt(i)-'0');
//        }
//        return sum;
//    }

}
