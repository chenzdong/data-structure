package com.czd.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * LRU(Least Recently Used )最近最少使用缓存
 *
 * @author: czd
 * @create: 2018/11/5 9:12
 */
public class LRUCache {
    private Node head;
    private Node end;
    //缓存存储最大数量
    private int max;
    private HashMap<String,Node> hashMap;

    public LRUCache(int max) {
        this.max = max;
        hashMap = new HashMap<>();
    }

    public String get(String key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return  null;
        }
        //更新Node
        refreshNode(node);
        return node.value;
    }

    public void put(String key,String value) {
        Node node = hashMap.get(key);
        if (node == null) {
            //key不存在则插入
            if (hashMap.size() >= max) {
                String oldkey =removeNode(head);
                hashMap.remove(oldkey);
            }
            node = new Node(key,value);
            addNode(node);
            hashMap.put(key,node);
        } else {
            //key存在则更新
            node.value = value;
            refreshNode(node);
        }
    }
    public void refreshNode(Node node) {
        if (node == end ) {
            return;
        }
        //从原位置移除
        removeNode(node);
        //插入节点
        addNode(node);
    }

    public String removeNode(Node node) {
        if (node == end) {
            end = end.pre;
        } else if (node == head) {
            head = head.next;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre ;
        }
        return node.key;
    }

    public void addNode(Node node) {
        if(end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null) {
            head = node;
        }
    }
    class Node {
        Node(String key,String value) {
            this.key = key;
            this.value = value;
        }
        public Node pre;
        public Node next;
        public String key;
        public String value;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put("001","用户1信息");
        lruCache.put("002","用户2信息");
        lruCache.put("003","用户3信息");
        lruCache.put("004","用户4信息");
        lruCache.put("005", "用户5信息");
        lruCache.get("002");
        lruCache.put("002", "用户2信息更新");
        lruCache.put("006", "用户6信息");
        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));
    }
}
