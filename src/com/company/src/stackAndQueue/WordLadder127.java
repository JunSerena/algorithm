package com.company.src.stackAndQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 127. 单词接龙
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 *
 * 每一对相邻的单词只差一个字母。
 *  对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
 *
 *
 * 示例 1：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 示例 2：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 *
 *
 * 提示：
 *
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 *
 *  * 难度：困难
 */
public class WordLadder127 {
    public static void main(String[] args) {
        String begin = "leet";
        String end = "code";
        List<String> list = new ArrayList<>();
        //["lest","leet","lose","code","lode","robe","lost"]
        list.add("lest");
        list.add("leet");
        list.add("lose");
        list.add("code");
        list.add("lode");
        list.add("robe");
        list.add("lost");
        System.out.println(ladderLength(begin,end, list));

    }
    /**
     * bfs广度优先搜索，相差一个字符的可以连接成一条边。
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //先检查 endWord在不在list中，不在就可以直接返回
        if (wordList==null|| !wordList.contains(endWord))
            return 0;
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        queue.add(beginWord);
        map.put(beginWord,1);  //根据题意初始为1

        //将wordList放到一个set中便于判断是否存在
        Set<String> set = new HashSet<>(wordList);

        int len = beginWord.length();
        while (!queue.isEmpty()){
            String s = queue.poll();

            if (s.equals(endWord)){
                return map.get(s);
            }

            for (int i = 0; i < len; i++) {
                int c = s.charAt(i)-'a';

                for (int j = 0; j <26 ; j++) {
                    if (j==c)
                        continue;
                    //String newS = s.replace(s.charAt(i), (char)(j+'a')); //不能这么写，如果s.charAt(i)有几个地方出现，都会被替换
                    StringBuilder stringBuilder = new StringBuilder(s);
                    stringBuilder.replace(i,i+1, (char)(j+'a')+"");
                    String newS = stringBuilder.toString();
                    if (set.contains(newS) && !map.containsKey(newS)){
                        queue.add(newS);
                        map.put(newS, map.get(s)+1);
                    }
                }
            }
        }

        return 0;
    }


}
