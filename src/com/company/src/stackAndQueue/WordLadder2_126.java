package com.company.src.stackAndQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 126. 单词接龙 II
 * 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：
 *
 * 每对相邻的单词之间仅有单个字母不同。
 * 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * 解释：存在 2 种最短的转换序列：
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * 示例 2：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：[]
 * 解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。、
 *
 * 难度：困难
 */
public class WordLadder2_126 {

    public static void main(String[] args) {

        String begin = "hit";
        String end = "cog";
        List<String> list = new ArrayList<>();
        //["lest","leet","lose","code","lode","robe","lost"]
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        System.out.println(findLadders2(begin,end, list));

    }

    // 和127号题的区别在于，此题需要记录路径，而不是单纯输出路径的长度
    // 直接改造 map，map之前保存当前节点和前面经过的步数，现在直接存经过的路径
    // todo 可以通过部分测试用例，但是超出了内存限制。因为map中保存了所有的前驱路径
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        //先检查 endWord在不在list中，不在就可以直接返回
        if (wordList==null|| !wordList.contains(endWord))
            return new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, List<List<String>>> map = new HashMap<>();
        queue.add(beginWord);
        List<List<String>> first = new ArrayList<>();
        first.add(Arrays.asList(beginWord));
        map.put(beginWord, first);  //根据题意初始为1

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
                    if (set.contains(newS) ){

                        if (!map.containsKey(newS)){
                            queue.add(newS);
                            //List<List<String>> pre = map.get(s);
                            map.put(newS, insetList(map.get(s), newS));

                        }else {   //如果包含的话，看路径
                            List<List<String>> list = map.get(newS);
                            int shortest = list.get(0).size();
                            List<List<String>> pre = map.get(s);
                            int size = pre.get(0).size();
                            if (size == shortest-1){ // 相同路径长度的话，就插入新的list中，否则就不插入
                                List<List<String>> newR = insetList(pre, newS); //先构建新的路径，再合并插入到map中
                                list.addAll(newR);
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>();

    }


    public static List<List<String>> insetList(List<List<String>> lists, String s){

        List<List<String>> result = new ArrayList<>();
        if (lists.size()==0){
            List<String > newList = new ArrayList<>();
            newList.add(s);
            result.add(newList);
            return result;
        }
        // 需要克隆
        for ( List<String> list: lists  ) {
            List<String> newList = new ArrayList<>(list);
            newList.add(s);
            result.add(newList);
        }
        return result;
    }


    /**
     * 尝试优化解法一，map不再存放完整路径，只存放步数相同的上一个节点。最后再根据前一个节点去溯源。
     * 代码通过。
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        //先检查 endWord在不在list中，不在就可以直接返回
        if (wordList==null|| !wordList.contains(endWord))
            return new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        Map<String, List<String>> nodeMap = new HashMap<>(); //用来保存图中节点和上一个节点的对应关系
        Map<String, Integer> countMap = new HashMap<>(); //用来保存抵达该节点所需最短路径
        queue.add(beginWord);

        nodeMap.put(beginWord, Arrays.asList("NULL"));
        countMap.put(beginWord, 1);

        //将wordList放到一个set中便于判断是否存在
        Set<String> set = new HashSet<>(wordList);

        int len = beginWord.length();
        while (!queue.isEmpty()){
            String s = queue.poll();

            if (s.equals(endWord)){
                // 根据nodeMap中的endWord去一个一个往前找，生成路径
                return  buildPath(nodeMap, endWord);
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
                    if (set.contains(newS) ){

                        if (!nodeMap.containsKey(newS)){
                            queue.add(newS);
                            List<String> list = new ArrayList<>();
                            list.add(s);
                            nodeMap.put(newS, list);
                            countMap.put(newS, countMap.get(s)+1);

                        }else {   //如果包含的话，看路径

                            int shortest = countMap.get(newS);
                            int curSize = countMap.get(s);
                            if (curSize == shortest-1){ // 相同路径长度的话，说明有其他节点可以最短路径到达该节点，插入list中，否则就不插入
                                nodeMap.get(newS).add(s);
                            }
                        }
                    }
                }
            }
        }


        return new ArrayList<>();
    }

    // 根据map中的的value是key的前一个节点，当成一个多叉树去层次遍历，遍历结果以头插法，将子节点插入列表的父节点前 否则会是倒序的。
    public static List<List<String>> buildPath(Map<String, List<String>> nodeMap, String end){
        //层次遍历，父子节点链接起来
        List<List<String>> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(end);
        // 把end放到列表中
        List<String> list1 = new LinkedList<>();
        list1.add(end);
        result.add(list1);

        while (!queue.isEmpty()) {

            String s = queue.poll();
            List<String> children = nodeMap.get(s);

            if (children.get(0).equals("NULL"))
                break;

            for (int k = 0; k < result.size() ; k++) {
                LinkedList<String>  linkedList = (LinkedList<String>) result.get(k);
                if (linkedList.getFirst().equals(s)){
                    // 复制j份
                    for (int j = 0; j <children.size()-1 ; j++) {
                        LinkedList<String>  linkedListNew = (LinkedList<String>) linkedList.clone();
                        linkedListNew.addFirst(children.get(j));
                        queue.add(children.get(j));
                        result.add(linkedListNew);
                    }
                    linkedList.addFirst(children.get(children.size()-1)); //最后一份不用复制，直接用原来的list就好了
                    queue.add(children.get(children.size()-1));
                }
            }


        }

        return result;

    }


}
