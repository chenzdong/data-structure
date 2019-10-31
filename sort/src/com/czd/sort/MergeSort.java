package com.czd.sort;

/**
 * 归并排序
 * 最好、平均、最坏时间复杂度O(nlogn)
 * 稳定排序：Y
 * 原地排序：N
 * 递推公式：merge_sort(p…r) = merge(merge_sort(p…q), merge_sort(q+1…r))
 * 终止条件：p >= r
 * @author: czd
 * @create: 2019-10-31 14:49
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] number={49,38,65,76,13,27,10,8,97};
        mergeSort(number);
        for (int i = 0; i < number.length; i++) {
            System.out.println(number[i]);
        }
    }
    private static void mergeSort(int[] numbers) {
        int size = numbers.length;
        if (size <= 1) {
            return;
        }
        merge_sort_c(numbers, 0, size-1);
    }

    /**
     * 归并排序 分治思想 递归手段
     * @param numbers 数据
     * @param start 开始位置
     * @param end 结束位置
     */
    private static void merge_sort_c(int[] numbers, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (start + end) / 2;
        merge_sort_c(numbers, start, middle);
        merge_sort_c(numbers, middle+1, end);
        merge(numbers, start, middle , end);
    }

    /**
     * 将numbers[start, middle] numbers[middle+1, end] 排序后放入numbers[start, end]
     * @param numbers
     * @param start
     * @param middle
     * @param end
     */
    private static void merge(int[] numbers, int start, int middle, int end) {
        int m = start;
        int n = middle+1;
        int k = 0;
        int[] result = new int[end-start+1];
        // 比较两个数组，每次取最小值存入result
        while (m <= middle && n <= end) {
            if (numbers[m] <= numbers[n]) {
                result[k++] = numbers[m++];
            } else {
                result[k++] = numbers[n++];
            }
        }
        // 判断哪个数组还剩数据,将其存入result
        int head,tail;
        if (m <= middle) {
            head = m;
            tail = middle;
        } else {
            head = n;
            tail = end;
        }
        while (head <= tail) {
            result[k++] = numbers[head++];
        }
        // 重新装入numbers数组
        for (int i = 0; i < end-start+1; i++) {
            numbers[start+i] = result[i];
        }

    }

}
