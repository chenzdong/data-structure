package queue;

/**
 * 循环队列
 *
 * @author: czd
 * @create: 2019-11-13 13:57
 */
public class CircularQueue {
    private String[] items;
    private int n;
    private int head;
    private int tail;
    public CircularQueue(int capacity) {
        this.items = new String[capacity];
        this.n = capacity;
    }
    public boolean enqueue(String value) {
        // 队列满了
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = value;
        tail = (tail + 1) % n;
        return true;
    }
    public String dequeue() {
        // 队列为空
        if (head == tail) {
            return null;
        }
        String value = items[head];
        head = (head + 1) % n;
        return value;
    }
}
