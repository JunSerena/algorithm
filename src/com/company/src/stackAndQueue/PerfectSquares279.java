package com.company.src.stackAndQueue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 *
 * 1 <= n <= 104
 */
public class PerfectSquares279 {
    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }

    /**
     * 解法：构建图，图的广度优先遍历。构建图的思路是，两个num节点，节点1-节点2的值为一个完全平方数，就可以将节点1和2连接起来，构建一个图。
     *
     * 广度优先遍历这个图：从n到0，记录数量n和走的步数step之间的关系。保存到queue里，重复的num不必再次保存，因为后面重复的num的step肯定比第一次出现的num的step要多。
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>(); //保存下一步要遍历的节点
        Map<Integer, Integer> map = new HashMap<>(); //保存遍历过的节点和最短的step

        queue.add(n);
        map.put(n,0);
        while (!queue.isEmpty()){
            int x = queue.poll();
            for (int i = 1; ; i++) {
                // 找到当前x所有可以连接的节点（广度优先）
                int dif = x-i*i;

                if (dif<0){  //当前节点的下一个节点已全部得到
                    break;
                }
                if (dif==0){
                    return map.get(x)+1;
                }
                if (!map.containsKey(dif)) { //重复的num不计入。
                    int step = map.get(x);
                    map.put(dif, step+1);
                    queue.add(dif);
                }

            }
        }
        return n; //最大的step就是n，n个1的和为n
    }

    /**
     * 解法二：动态规划，求最优子结构。n可以分为 i*i + left，一旦left=0，说明自身为完全平方数，无需分解，否则，求left的解
     * @param n
     * @return
     */
    public static int numSquares2(int n) {
        int[] dp = new int[n+1];
        dp[0] =0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) { // 从小到大，找到最优子结构
            int tmp = i+1;
            for (int j = 1; ; j++) {
                int l = i-j*j;
                if (l<0)
                    break;
                if (l==0){ // 自身就是完全平方数，不需要分解。
                    tmp=1;
                    break;
                }
                tmp = Math.min(tmp, 1+dp[l]);
            }
            dp[i] =tmp;
        }
        return dp[n];
    }
}
