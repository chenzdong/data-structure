package com.czd.sort.n2;

import java.util.Arrays;

/**
 * 插入排序
 * @author: czd
 * @create: 2020-05-06 14:50
 */
public class InsertSort {
    private static int[] insertSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int length = nums.length;
        for (int i = 0; i < length-1; i++) {
            int j = i + 1;
            boolean swap = false;
            while (j > 0 && nums[j] < nums[j-1]) {
                int tmp = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = tmp;
                j--;
                swap = true;
            }
            if (!swap) {
                break;
            }
        }
        return nums;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{9,7,1,2,5,4,8};
        insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
