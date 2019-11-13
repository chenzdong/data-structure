package queue;

/**
 * 链表实现队列
 *
 * @author: czd
 * @create: 2019-11-12 15:55
 */
public class LinkedListQueue {
    private Node head;
    private Node tail;
    private static class Node {
        private Node next;
        private String data;

        public Node(String data) {
            this.data = data;
        }
        public String getData() {
            return this.data;
        }
    }
    public String dequeue() {
        if (head == null) {
            return  null;
        }
        String value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }
    public void enqueue(String value) {
        Node node = new Node(value);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }


}
