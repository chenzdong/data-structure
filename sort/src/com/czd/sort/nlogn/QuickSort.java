package com.czd.sort.nlogn;

import java.util.Arrays;

/**
 * 快速排序
 * int index = partition(nums,start,end)
 * quick_sort(nums,start,index-1)
 * quick_sort(nums,index+1,end)
 * @author: czd
 * @create: 2020-05-07 16:30
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = new int[]{8,1,5,7,9,6,2};
        quickSort(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int index = partition(nums, start, end);
        quickSort(nums, start, index-1);
        quickSort(nums, index+1, end);
    }
    private static int partition(int[] nums, int start, int end) {
        // 基准元素
        int pivot = nums[end];
        int index = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] < pivot) {
                int tmp = nums[i];
                nums[i] = nums[index];
                nums[index] = tmp;
                index++;
            }
        }
        int tmp = nums[index];
        nums[index] = pivot;
        nums[end] = tmp;
        return index;
    }
}
