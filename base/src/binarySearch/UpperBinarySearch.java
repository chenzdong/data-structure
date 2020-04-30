package binarySearch;

/**
 * 变种的二分查找
 * 1. 查找第一个等于定值的元素
 * 2. 查找最后一个等于定值的元素
 * 3. 查找第一个大于等于给定值的元素
 * 4. 查找最后一个小于等于定值的元素
 * @author: czd
 * @create: 2020-04-30 10:05
 */
public class UpperBinarySearch {
    /**
     * 查找第一个等于定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearch1(int[] a, int n, int value) {
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int middle = start + ((end - start) >> 1);
            if (a[middle] < value) {
                start = middle++;
            } else if (a[middle] > value) {
                end = middle--;
            } else {
                if (middle == 0 || a[middle-1] != value) {
                    return middle;
                }
                end = middle--;

            }
        }
        return -1;
    }

    /**
     * 查找最后一个等于定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearch2(int[] a, int n, int value) {
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int middle = start + ((end - start) >> 1);
            if (a[middle] < value) {
                start = middle++;
            } else if (a[middle] > value) {
                end = middle--;
            } else {
                if (middle == n - 1 || a[middle+1] != value) {
                    return middle;
                }
                start = middle++;

            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearch3(int[] a, int n, int value) {
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int middle = start + ((end - start) >> 1);
            if (a[middle] < value) {
                start = middle++;
            } else {
                if (middle == 0 || a[middle - 1] < value) {
                    return middle;
                }
                end = middle--;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearch4(int[] a, int n, int value) {
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int middle = start + ((end - start) >> 1);
            if (a[middle] > value) {
                end = middle --;
            } else {
                if (middle == n - 1 || a[middle + 1] > value) {
                    return middle;
                }
                start = middle++;
            }
        }
        return -1;
    }
}
