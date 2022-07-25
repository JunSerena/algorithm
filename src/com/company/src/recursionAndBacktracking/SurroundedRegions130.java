package com.company.src.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 示例 2：
 *
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 * 链接：https://leetcode.cn/problems/surrounded-regions/
 */
public class SurroundedRegions130 {

    public static void main(String[] args) {
        SurroundedRegions130 ins = new SurroundedRegions130();
        //char[][] grid = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        char[][] grid = {{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}};
        //char[][] grid = {{'O','O','O'},{'O','O','O'},{'O','O','O'}};
        ins.solve(grid);
    }

    int m=0; // m行n列
    int n=0;
    int[][] d = {{-1,0}, {0,1}, {1,0},{0,-1}};

    /**
     * 深度优先遍历。从四条边界出发，遇到O就标记为A，表面此O是直接或者间接与边界关联。不能变为X。最后剩下的O需要改为X，A改为O。
     *
     * 需要反向思考：需要替换O的，一定不在边界上。所以先找出直接或间接与边界O相连接的点。
     */
    public void solve(char[][] board) {
        m = board.length; //m行n列
        n= board[0].length;

        if (m==1||n==1)
            return;

        for (int i = 0; i <m ; i++) {
            dfs(board, i, 0 );  //每一行的第一个和最后一个元素
            dfs(board, i, n-1 );
        }

        for (int i = 1; i <n ; i++) {
            dfs(board, 0, i );  //每一列的第一个和最后一个元素
            dfs(board, m-1, i );
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j <n ; j++) {
                if (board[i][j]=='A'){
                    board[i][j]='O';
                }else if (board[i][j]=='O')
                    board[i][j]='X';
            }
        }

    }

    public void dfs(char[][] board, int x, int y) {
        if (!inArea(x,y) || board[x][y]!='O')
            return ;

        board[x][y]='A'; //先把O标记为A

        for (int i = 0; i < 4; i++) {
            int nexX = d[i][0]+x;
            int nexY = d[i][1]+y;
            dfs(board,nexX,nexY);
        }

    }

    public boolean inArea(int x, int y){
        return x>=0&&x<m && y>=0&&y<n;
    }
}
