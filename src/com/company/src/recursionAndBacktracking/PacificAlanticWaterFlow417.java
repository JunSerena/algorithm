package com.company.src.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 417. 太平洋大西洋水流问题
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 *
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 *
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 *
 * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
 *
 *
 *
 * 示例 1：
 *
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 示例 2：
 *
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 *
 * 链接：
 */
public class PacificAlanticWaterFlow417 {

    public static void main(String[] args) {
        PacificAlanticWaterFlow417 ins = new PacificAlanticWaterFlow417();
        int[][] he = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(ins.pacificAtlantic(he));
    }


    /**
     * 反向寻找：水往高处溯源。分别从四条边开始寻找，标记可以从边界到达的节点。左上的存一个表，右下的存一个表
     * 寻找公共节点。
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] alantic = new boolean[m][n];

        // 上下边界
        for (int i = 0; i <n ; i++) {
            dfs(heights,0,i,0,pacific);
            dfs(heights,m-1,i,0,alantic);
        }

        // 左右边界（注意四个角不能去重）
        for (int i = 0; i <m ; i++) {
            dfs(heights,i,0,0,pacific);
            dfs(heights,i,n-1,0,alantic);
        }

        //遍历，将pacific和alantic都为true的摘出来
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if (pacific[i][j] && alantic[i][j])
                    list.add(Arrays.asList(i,j));
            }

        }

        return list;

    }


    int m=0; // m行n列
    int n=0;
    int[][] d = {{-1,0}, {0,1}, {1,0},{0,-1}};

    //从边界寻找, 从低到高, 标记经过的坐标
    public void dfs(int[][] heights, int x, int y, int lastHeight, boolean[][] sea){
        if (!inArea(x,y) || sea[x][y]){
            return;
        }
        if (lastHeight>heights[x][y]){
            return;
        }
        sea[x][y]=true;
        for (int i = 0; i <4 ; i++) {
            int nexX = d[i][0]+x;
            int nexY = d[i][1]+y;
            dfs(heights, nexX,nexY, heights[x][y], sea);
        }

    }

    public boolean inArea(int x, int y){
        return x>=0&&x<m && y>=0&&y<n;
    }
}
