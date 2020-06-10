package queue;

/**
 * 链表实现队列
 *
 * @author: czd
 * @create: 2020-06-02 10:41
 */
public class QueueByLinkedList {
    private Node head;
    private Node tail;
    public void enqueue(String data) {
        Node node = new Node(data);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }
    public String dequeue() {
       if (head == null) {
           return null;
       }
       String data = head.getData();
       head = head.next;
       if (head == null) {
           tail = null;
       }
       return data;
    }


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

}
