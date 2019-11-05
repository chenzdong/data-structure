package stack;

import java.util.HashMap;

/**
 * LRU(Least Recently Used )最近最少使用缓存
 * 使用单向有序列表 越早进去的元素越靠近尾部
 * 数据访问时：
 * 1. 已经在链表中，删除原本的位置 然后插入到头部
 * 2. 不在链表中，i.缓存未满 直接加入 ii.缓存满了 删除尾部节点，将数据插入头部
 * hashMap记录位置
 * @author: czd
 * @create: 2018/11/5 9:12
 */
public class LRUCache {
    private Node head;
    private Node end;
    /** 缓存存储最大数量 **/
    private int max;
    /** 记录位置 **/
    private HashMap<String,Node> hashMap;

    public LRUCache(int max) {
        this.max = max;
        hashMap = new HashMap<>();
    }
    public void put(String key, String value) {
        Node node = hashMap.get(key);
        // node不存在则插入链表 存在则更新位置
        if (node == null) {
            // 插入时考虑链表容量是否已满
            if (hashMap.size() >= max) {
                String oldkey = removeNode(end);
                hashMap.remove(oldkey);
            }
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        } else {
            node.value = value;
            refreshNode(node);
        }
    }
    public void refreshNode(Node node) {
        if (node == null) {
            return;
        }
        removeNode(node);
        addNode(node);
    }
    // 添加到头部
    public void addNode(Node node) {
        if (node == null) {
            return;
        }
        if (head != null) {
            node.next = head;
            head.pre = node;
        }
        if (end == null) {
            end = node;
        }
        head = node;
    }
    // 删除数据
    public String removeNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node == head) {
            head = head.next;
        } else if (node == end) {
            end = end.pre;
        } else {
            node.next.pre = node.pre;
            node.pre.next = node.next;
        }
        return node.key;
    }
//    public String get(String key) {
//        Node node = hashMap.get(key);
//        if (node == null) {
//            return null;
//        }
//        //更新Node
//        refreshNode(node);
//        return node.value;
//    }
//
//    public void put(String key,String value) {
//        Node node = hashMap.get(key);
//        if (node == null) {
//            //key不存在则插入
//            if (hashMap.size() >= max) {
//                String oldkey = removeNode(head);
//                hashMap.remove(oldkey);
//            }
//            node = new Node(key,value);
//            addNode(node);
//            hashMap.put(key,node);
//        } else {
//            //key存在则更新
//            node.value = value;
//            refreshNode(node);
//        }
//    }
//    public void refreshNode(Node node) {
//        if (node == end ) {
//            return;
//        }
//        //从原位置移除
//        removeNode(node);
//        //插入节点
//        addNode(node);
//    }
//
//    public String removeNode(Node node) {
//        if (node == end) {
//            end = end.pre;
//        } else if (node == head) {
//            head = head.next;
//        } else {
//            node.pre.next = node.next;
//            node.next.pre = node.pre ;
//        }
//        return node.key;
//    }
//
//    public void addNode(Node node) {
//        if(end != null) {
//            end.next = node;
//            node.pre = end;
//            node.next = null;
//        }
//        end = node;
//        if (head == null) {
//            head = node;
//        }
//    }
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
        lruCache.put("001", "用户1信息更新");
        lruCache.put("006", "用户6信息");
    }
}
