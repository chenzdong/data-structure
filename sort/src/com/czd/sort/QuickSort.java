package com.czd.sort;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 快速排序
 * 最好、平均时间复杂度O(nlogn) 、最坏时间复杂度O(n2)
 * 稳定排序：N
 * 原地排序：Y
 * 递推公式：quick_sort(p…r) = quick_sort(p…q-1) + quick_sort(q+1… r)
 * 终止条件：p >= r
 * @author: czd
 * @create: 2018/10/29 15:50
 */
public class QuickSort {
    private  static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {
        int[] arr = new int[]{4,7,6,5,3,2,8,1};
        quickSort(arr,0,arr.length-1);
        System.out.println(count.get());
        System.out.println(Arrays.toString(arr));
    }
    /**
     * 快速排序 （挖坑法,递归）
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    private static void quickSort(int[] arr, int startIndex, int endIndex) {
        //结束递归
        if (startIndex >= endIndex){
            return;
        }
        //得到排序后基准元素位置
        int pivotIndex = partition(arr,startIndex,endIndex);
        //分治法递归数列两部分
        quickSort(arr,startIndex,pivotIndex-1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    /**
     * 找到基准元素并大致排序
     * @param arr
     * @param startIndex
     * @param endIndex
     * @return 基准元素的位置
     */
    private static int partition1(int[] arr, int startIndex, int endIndex) {
        //基准元素
        int pivot = arr[startIndex];
        //左指针
        int left = startIndex;
        //右指针
        int right = endIndex;
        //基准位置
        int index = startIndex;
        while (right >= left) {
            while (right >= left) {
                count.getAndAdd(1);
                if (arr[right] < pivot) {
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;

            }

            while (right >= left) {
                count.getAndAdd(1);
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }
    private static int partition(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int index = endIndex;
        for (int i = endIndex; i >= startIndex ; i--) {
            if (arr[i] > pivot) {
                // swap arr[i] and arr[index]
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index--] = temp;
            }
        }
        int temp = arr[index];
        arr[index] = arr[startIndex];
        arr[startIndex] = temp;
        return index;
    }
}
