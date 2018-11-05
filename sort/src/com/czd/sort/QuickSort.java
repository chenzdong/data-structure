package com.czd.sort;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 快速排序
 *
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
    private static int partition(int[] arr, int startIndex, int endIndex) {
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
}
