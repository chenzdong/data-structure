package com.czd.sort.n2;

import java.util.Arrays;

/**
 * 冒泡排序测试
 * 从小到大排序
 * 稳定排序
 * 原地排序
 * @author: czd
 * @create: 2020-05-06 14:15
 */
public class BubbleSort {
    public static int[] bubbleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int length = nums.length;
        for (int i = 0; i < length-1; i++) {
            boolean swap = false;
            for (int j = 0; j < length-1-i; j++) {
                if (nums[j] > nums[j+1]) {
                    // 交换
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
        return nums;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{9,7,1,2,5,4,8};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
