package queue;

/**
 * 数组实现队列
 *
 * @author: czd
 * @create: 2019-11-12 15:21
 */
public class ArrayQueue {
    private String[] items;
    private int n = 0;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        this.items = new String[capacity];
        this.n = capacity;
    }

    public boolean enqueue(String item) {
        if (tail == n) {
            // 会遇到一种情况 tail为n可head不为0 说明数组未满会触发数据迁移
            if (head == 0) {
                return false;
            }
            // 数据迁移
            for (int i = head; i < tail; i++) {
                items[i-head] = items[i];
            }
            tail -= head;
            head = 0;
        }
        items[tail++] = item;
        return true;
    }
    public String dequeue() {
        if (head == tail) {
            return null;
        }

        return items[head++];
    }
}
