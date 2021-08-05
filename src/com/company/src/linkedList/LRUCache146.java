package com.company.src.linkedList;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 *
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 */

import java.util.HashMap;

/**
 * 思路：
 * - 键值对映射关系：map->hashMap
 * - 需要方便的变更顺序：链表，链表的插入删除都很方便。
 * - O(1)时间复杂度，且需要方便的变更链表各个节点数据：双向链表（双向链表才能更快的找到prev节点）
 *
 * 思路二：
 *  看leetcode 里面有人用LinkedHashMap实现。。。。不需要自己定义双向链表的结构。下次研究一下LinkedHashMap的内部实现。
 */
public class LRUCache146 {

    //节点的定义
    private class CacheNode{
        CacheNode prev;
        CacheNode next;
        int key;
        int value;

        public CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    //定义一些需要的变量
    private int capacity;
    private HashMap<Integer, CacheNode> hashMap = new HashMap<>();
    private CacheNode head = new CacheNode(-1,-1); //哨兵节点
    private CacheNode tail = new CacheNode(-1,-1);


    public LRUCache146(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    // 1、根据key获取对应的value
    // 2、找到的话，把那个节点移动到队尾
    // 3、找不到，返回-1
    public int get(int key) {
        if (!hashMap.containsKey(key))
            return -1;
        CacheNode current = hashMap.get(key);
        //把当前节点摘出来
        current.prev.next = current.next;
        current.next.prev = current.prev;
        //移动到队尾
        moveToTail(current);
        return current.value;
    }
    private void moveToTail(CacheNode node){
        //将当前节点与tail节点的前驱节点绑定关系
        tail.prev.next = node;
        node.prev = tail.prev;

        //将当前节点与tail节点绑定关系
        node.next = tail;
        tail.prev = node;


    }

    //1、寻找对应的键，存在，则放至队尾（可借助get函数）
    //3、若不存在，则新增一个放至队尾、超出内存时，删除队首节点
    public void put(int key, int value) {
        //get函数将当前node移动至队尾，再更新值
        if (get(key)!=-1){
            tail.prev.value = value;
            return;
        }

        //不存在时且超出容量，删除队首
        if (capacity==hashMap.size()){
            hashMap.remove(head.next.key);   //记得删除 hashmap里的对象
            head.next = head.next.next;
            head.next.prev = head;

        }
        //新增一个放队尾
        CacheNode newOne = new CacheNode(key,value);
        hashMap.put(key,newOne);  //记得放进hashmap里
        moveToTail(newOne);
    }

}
