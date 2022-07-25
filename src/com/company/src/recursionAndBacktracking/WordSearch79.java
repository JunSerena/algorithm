package com.company.src.recursionAndBacktracking;

/**
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 *
 *链接：https://leetcode.cn/problems/word-search/
 */
public class WordSearch79 {

    public static void main(String[] args) {
        // new char[3][4]
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};

        WordSearch79 wordSearch = new WordSearch79();
        System.out.println(wordSearch.exist(board,"AB"));
    }

    /**
     * 解法一，回溯法
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <board[i].length ; j++) {
                if (search2(board,i,j,word,0))
                    return true;
            }
        }
        return false;

    }

    boolean[][] visited;
    // 访问顺序，上右下左顺时针访问
    boolean exit=false;
    public boolean search(char[][] board, int x, int y, String word, int wordIndex){

        if (wordIndex==word.length()){
            exit=true;
            return true;
        }

        if ( x>=board.length || x<0 || y<0 || y>=board[0].length)
            return false;

        // 访问过
        if (visited[x][y]){
            return false;
        }

        if (board[x][y]!=word.charAt(wordIndex))
            return false;
        wordIndex++;

        //for (int i = 0; i <4 ; i++) {
            if (exit)
                return true;
            //上
            visited[x][y]=true;
            search(board, x-1, y,word, wordIndex);

            //右
            search(board, x, y+1,word, wordIndex);

            //下
            search(board, x+1, y,word, wordIndex);

            //左
            search(board, x, y-1,word, wordIndex);
            visited[x][y] = false;
        //}

        return exit;

    }


    /**
     * 解法二：优化search函数
     * @param board
     * @param x
     * @param y
     * @param word
     * @param wordIndex
     * @return
     */
    int[][] d = {{-1,0},{0,1},{1,0},{0,-1}};
    public boolean search2(char[][] board, int x, int y, String word, int wordIndex){
        if (wordIndex==word.length()-1){
            return board[x][y]==word.charAt(wordIndex);
        }


        if (board[x][y]==word.charAt(wordIndex)){
            visited[x][y]=true;
            for (int i = 0; i <4 ; i++) {
                int newX = x+d[i][0];
                int newY = y+d[i][1];

                if (inArea(board, newX, newY) && !visited[newX][newY]){
                    if (search2(board,newX,newY,word,wordIndex+1))
                        return true;
                }
            }

            visited[x][y]=false;
        }

        return false;
    }

    public boolean inArea( char[][] board, int x, int y){
        return x < board.length && x >= 0 && y >= 0 && y < board[0].length;
    }

}
