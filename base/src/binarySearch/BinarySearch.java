package binarySearch;

/**
 * 二分查找
 * 时间复杂度O(logn)，适用于静态数据
 * 局限性:适用于有序结构 数组；数据量太少和顺序遍历相差不大
 * @author: czd
 * @create: 2019-12-26 14:34
 */
public class BinarySearch {
    /**
     * 二分查找
     * @param a 原数组
     * @param n 数组长度
     * @param value 待寻找的值
     * @return 值对应的位置
     */
    public int bsearch(int[] a, int n ,int value) {
//        int low = 0;
//        int high = n - 1;
//        while (low <= high) {
//            int mid = low + (high - low)/2;
//            if (a[mid] == value) {
//                return mid;
//            } else if(a[mid] < value) {
//                low = mid + 1;
//            } else {
//                high = mid - 1;
//            }
//        }
//        return -1;
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int middle = start + ((end - start) >> 1);
            if (a[middle] == value) {
                return middle;
            } else if (a[middle] < value) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找递归实现
     * @param a 数组
     * @param n 数组长度
     * @param value 待寻找的值
     * @return 值index
     */
    public int RecursiveSearch(int[] a, int n ,int value) {
        return bsearchInternally(a, 0, n-1, value);
    }

    private int bsearchInternally(int[] a, int low, int high, int value) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchInternally(a, mid++, high, value);
        } else {
            return bsearchInternally(a, low, mid--, value);
        }
    }

}
