package com.czd.sort.n;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 桶排序
 *
 * @author: czd
 * @create: 2020-05-06 16:08
 */
public class BucketSort {
    private static double[] bucketSort(double[] nums, int bucketNum) {
        int length = nums.length;
        double max = nums[0];
        double min = nums[0];
        for (int i = 0; i < length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        double d = max - min;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }
        for (int i = 0; i < length; i++) {
            int num =  (int) ((nums[i]-min) * (bucketNum - 1) / d);
            bucketList.get(num).add(nums[i]);
        }
        // 桶内排序
        for (int i = 0; i < bucketNum; i++) {
            Collections.sort(bucketList.get(i));
        }
        // 输出
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            for (Double num : bucketList.get(i)) {
                nums[index++] = num;
            }
        }
        return nums;

    }

    public static void main(String[] args) {
        double[] nums = new double[]{1,0.002d,8.9d,2.5,5.2,2,2.1,1};
        bucketSort(nums, 2);
        System.out.println(Arrays.toString(nums));
    }
}
