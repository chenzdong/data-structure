package com.czd.sort.nlogn;

import java.util.Arrays;

/**
 * 归并排序
 * merge_sort = merge(merge(nums,i,j),merge(nums,j+1,k))
 * i >= j
 * @author: czd
 * @create: 2020-05-07 11:07
 */
public class MergeSort {
    private static void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = start + ((end-start) >> 1);
        mergeSort(nums, 0, middle);
        mergeSort(nums, middle+1, end);
        merge(nums, start, middle, end);
    }
    private static void merge(int[] nums, int start, int middle, int end) {
        int left = start;
        int right = middle+1;
        int[] result = new int[end-start+1];
        int index = 0;
        while (left <= middle && right <= end) {
            if (nums[left] <nums[right]) {
                result[index++] = nums[left++];
            } else {
                result[index++] = nums[right++];
            }
        }
        if (left <= middle) {
            while(left <= middle) {
                result[index++] = nums[left++];
            }
        } else {
            while(right <= end) {
                result[index++] = nums[right++];
            }
        }
        for (int i = 0; i < end-start+1; i++) {
            nums[start+i] = result[i];
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,8,5,7,9,6,2};
        mergeSort(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
}
