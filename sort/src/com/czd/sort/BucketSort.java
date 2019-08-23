package com.czd.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 桶排序 （计数排序升级版，适用于double）
 *
 * @author: czd
 * @create: 2018/10/22 15:32
 */
public class BucketSort {
    /**
     * 空间O（n+m） n 元素数量 m 桶数量
     * 时间O(n+m+n(logn-logm)）
     */
    public static double[] bucketSort(double[] arrays) {
        //先取出最大值
        if (arrays.length == 0) {
            return arrays;
        }
        double max = arrays[0];
        double min = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i] > max) {
                max = arrays[i];
            }
            if (arrays[i] < min) {
                min = arrays[i];
            }
        }
        double d = max-min;
        //初始化桶
        int bucketNum = arrays.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>();
        for (int i = 0; i <bucketNum ; i++) {
            bucketList.add(new LinkedList<Double>());
        }
        //将元素存入相应的桶中
        for (int i = 0; i <arrays.length ; i++) {
            //元素处于那个桶中
            int num =(int)((arrays[i]-min) * (bucketNum-1)/d);
            bucketList.get(num).add(arrays[i]);
        }
        //桶中重新排序
        for (int i = 0; i <bucketNum ; i++) {
            //O（nlogn）
            Collections.sort(bucketList.get(i));
        }
        //输出结果数组
        double[] result = new double[arrays.length];
        int index = 0;
        for (LinkedList<Double> list:bucketList) {
            for (double element:list) {
                result[index] = element;
                index++;
            }
        }
        return  result;
    }

    public static void main(String[] args) {
        double[] array = new double[]{4.12,6.421,0.0023,3.0,2.123,8.122,4.12,10.09,10000};
        double[] result = bucketSort(array);
        System.out.println(Arrays.toString(result));
    }
}
