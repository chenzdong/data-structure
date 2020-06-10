package tree;

/**
 * 堆
 *
 * @author: czd
 * @create: 2020-06-10 15:14
 */
public class Heap {
    // 从下标1开始存储数据
    private  int[] a;
    // 最大数据个数
    private int size;
    // 已经存储的数据个数
    private int count;

    public Heap(int capacity) {
        a = new int[capacity+1];
        size = capacity;
        count = 0;
    }

    /**
     * 新增节点
     * @param data
     */
    public void insert(int data) {
        if (count >= size) {
            return;
        }
        a[++count] = data;
        int i = count;
        // 从下往上交换
        while (i/2 > 0 && a[i] > a[i/2]) {
            swap(a, i, i/2);
            i = i/2;
        }
    }
    public void removeMax() {
        if (count == 0) {
            return;
        }
        a[1] = count;
        count--;
        heapify(a, count, 1);

    }
    public void sort(int[] a, int n) {
        bulidHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1 , k);
            --k;
            heapify(a, k, 1);
        }
    }
    private void bulidHeap(int[] a, int n) {
        for (int i = n/2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }
    /**
     * 重新构建堆
     * @param a
     * @param n
     * @param i
     */
    private void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(a, i, maxPos);
            i = maxPos;
        }
    }
    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
