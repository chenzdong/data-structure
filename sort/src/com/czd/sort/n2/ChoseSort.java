package com.czd.sort.n2;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author: czd
 * @create: 2020-05-06 14:40
 */
public class ChoseSort {
    public static int[] choseSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int length = nums.length;
        for (int i = 0; i < length -1; i++) {
            // 每次选择剩下的最小的数交换到对应位置
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int tmp = nums[i];
                nums[i] = nums[min];
                nums[min] = tmp;
            }
        }
        return nums;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{9,7,1,2,5,4,8};
        choseSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
