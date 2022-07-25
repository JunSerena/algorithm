package com.company.src.recursionAndBacktracking;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 * 链接：https://leetcode.cn/problems/number-of-islands/
 */
public class NumberOfIslands200 {
    public static void main(String[] args) {
        NumberOfIslands200 numberOfIslands = new NumberOfIslands200();
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(numberOfIslands.numIslands(grid));
    }

    int number=0;
    int m=0; // m行n列
    int n=0;
    public int numIslands(char[][] grid) {
        m = grid.length;
        n= grid[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <n ; j++) {
                if (grid[i][j]=='1'&&!visited[i][j]){
                    number++; //每次进入递归函数，都说明找到了一个岛屿
                    dfs(grid,i,j);
                }

            }

        }
        return number;
    }

    int[][] d = {{-1,0}, {0,1}, {1,0},{0,-1}};
    boolean[][] visited;

    // 深度优先遍历，上下右左的方向
    public void dfs(char[][] grid, int x, int y){

        // 周边都是水域或者都遍历过了，number+1
        if (grid[x][y]=='1'){
            visited[x][y]=true;
            for (int i = 0; i <4 ; i++) {
                int nexX = x+d[i][0];
                int nexY = y+d[i][1];
                if (inArea(nexX,nexY)&&!visited[nexX][nexY]){
                    dfs(grid,nexX,nexY);
                }
            }
        }

    }

    public boolean inArea(int x, int y){
        return x>=0&&x<m && y>=0&&y<n;
    }
}
