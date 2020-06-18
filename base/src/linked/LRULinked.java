package linked;


import stack.LRUCache;

import java.util.HashMap;
import java.util.Map;

/**
 * 利用链表实现LRU算法（最近使用的放在头部）
 * 相关操作：
 * 1. 增加 ：i 在链表里 ii 不在链表里 （满了和没满）
 * 2. 修改对应元素
 * 3. 使用
 * 4. 删除
 * @author: czd
 * @create: 2020-05-08 13:33
 */
public class LRULinked {
    private int capacity;
    private Node head;
    private Node tail;
    /**
     *    用于存储节点，查询更快，减少遍历链表操作
     *
     */
    private Map<String, Node> map;
    public LRULinked(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    /**
     * 添加节点
     * i 节点已存在 移动节点
     * ii 节点不存在 判断满没满 满了就删除尾结点 然后加到头部
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        Node n = map.get(key);
        if (n == null) {
            // 满了
            if (map.size() == capacity) {
                map.remove(tail.key);
                removeNode(tail);
            }
            Node node = new Node(key, value);
            addNode(n);
            map.put(key, node);
        } else {
            n.value = value;
            removeNode(n);
            addNode(n);
        }
//        Node n = map.get(key);
//        if (n == null) {
//            if (map.size() == capacity) {
//                map.remove(tail.key);
//                removeNode(tail);
//            }
//            Node node = new Node(key, value);
//            addNode(node);
//            map.put(key, node);
//        } else {
//            n.value = value;
//            removeNode(n);
//            addNode(n);
//        }
    }
    /**
     * 将节点添加到头部
     * 头部为空
     * 尾部为空
     * @param node
     */
    public void addNode(Node node) {
        if (node == null) {
            return;
        }
        if (head != null) {
            node.next =head;
            head.pre = node;
        }
        if (tail == null) {
            tail = node;
        }
        head = node;
    }

    /**
     * 删除某一节点
     * 是头节点
     * 是尾结点
     * 中间节点
     * @param node
     * @return
     */
    public String removeNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node == head) {
            head = head.next;
        } else if (node == tail) {
            tail = tail.pre;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
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
}
