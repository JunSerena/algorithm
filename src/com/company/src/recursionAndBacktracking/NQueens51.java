package com.company.src.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[["Q"]]
 */
public class NQueens51 {

    public static void main(String[] args) {
        NQueens51 queens = new NQueens51();
        System.out.println(queens.solveNQueens(4));
    }

    /**
     * 解法：需要借助三个辅助空间：clo[x]表示第N列上有一个皇后，dia1[a]和dia2[b]分别表示对角线上是否有皇后
     * 逐层遍历。将皇后放在每层的节点上。
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        clo = new boolean[n];
        dia1 = new boolean[2*n-1];
        dia2 = new boolean[2*n-1];

        List<List<Integer>> ans = new ArrayList<>();

        dfs(n,0,ans);


        return res;
    }

    List<List<String>> res = new ArrayList<>();
    boolean[] clo;
    boolean[] dia1; // i=x+y, 左上方向的对角线，长度为2n-1;
    boolean[] dia2; // i=x-y+n-1. 右下方向对角线。 长度为2n-1;


    // x表示第x行
    public void dfs(int n,  int x, List<List<Integer>> ans) {
        if (x==n){
            res.add(generate(ans));
            return;
        }

        for (int j = 0; j < n; j++) {
            if (clo[j] || dia1[x+j] || dia2[x-j+n-1]) // 列 或者对角线上有皇后，则跳过该列。
                continue;
            clo[j] =true;
            dia1[x+j] = true;
            dia2[x-j+n-1] = true;
            ans.add(Arrays.asList(x,j));
            dfs(n, x+1, ans);
            clo[j] =false;
            dia1[x+j] = false;
            dia2[x-j+n-1] = false;
            ans.remove(ans.size()-1);
        }

    }

    // 生成N皇后的表达式
    public List<String> generate(List<List<Integer>> ans){
        List<String> result = new ArrayList<>();

        int n = ans.size();
        for (int i = 0; i < n; i++) {

            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j <n ; j++) {
                if (ans.get(i).get(1)==j){
                    stringBuilder.append("Q");
                }else
                    stringBuilder.append(".");
            }

            result.add(stringBuilder.toString());
        }

        return result;
    }


}
