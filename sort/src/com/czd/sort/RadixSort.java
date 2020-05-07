package com.czd.sort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @author: czd
 * @create: 2020-05-06 16:34
 */
public class RadixSort {
    private static int[] radixSort(int[] nums) {
        int length = nums.length;
        // 获取最大值
        int max = nums[0];
        for (int i = 1; i < length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        // 最大值的位数
        int count = 1;
        int n = max / 10;
        while (n > 0) {
            count++;
            n = n / 10;
        }
        for (int i = 0; i < count; i++) {
            nums = countSort(nums, i);
        }
        return nums;
    }
    private static int[] countSort(int[] nums, int round) {
        // 计数排序
        int[] bucket = new int[10];
        for (int i = 0; i < nums.length; i++) {
            int value = (int)(nums[i] / Math.pow(10, round)) % 10;
            bucket[value]++;
        }
        for (int i = 1; i < bucket.length; i++) {
            bucket[i] += bucket[i-1];
        }

        int[] result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = (int)(nums[i] / Math.pow(10, round)) % 10;
            result[bucket[index]-1] = nums[i];
            bucket[index]--;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1234,55,444,8,56,2222};
        nums = radixSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
