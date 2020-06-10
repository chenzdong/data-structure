package com.czd.sort.n;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 计数排序
 * @author: czd
 * @create: 2020-05-06 15:18
 */
public class CountSort {
    private static int[] countSort(int[] nums) {
        if (nums == null  || nums.length < 2) {
            return  nums;
        }
        int max = nums[0];
        int min = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        int[] tmp = new int[max - min + 1];
        for (int i = 0; i < length; i++) {
            int value = nums[i] - min;
            tmp[value]++;
        }
        int index = 0;
        for (int i = 0; i < tmp.length; i++) {
            while (tmp[i] > 0) {
                nums[index++] = min + i;
                tmp[i]--;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,1,8,9,2,3,3,5};
        countSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
