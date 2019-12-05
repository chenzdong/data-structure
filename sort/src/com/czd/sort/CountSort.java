package com.czd.sort;

import java.util.Arrays;

/**
 * 计数排序
 *
 * @author: czd
 * @create: 2018/10/22 13:59
 */
public class CountSort {
    public static void main(String[] args) {
        int[] array = new int[]{94,95,91,96,98,99,90,93,91,92};
        int[] result = countSort(array);
        System.out.println(Arrays.toString(result));
    }

    //计数排序 只适用于int,这个排序空间浪费较大，下一步应该从min-max建立数组
    public static int[] countSort(int[] temp) {
        //先取出最大值
        if (temp.length <= 1) {
            return temp;
        }
        int max=temp[0];
        int min=temp[0];
        for (int i = 1; i <temp.length; i++) {
            if (temp[i] > max) {
                max = temp[i];
            }
            if (temp[i] < min) {
                min = temp[i];
            }
        }
        //初始化计数数组
        int[] countArray = new int[max-min+1];
        for (int i = 0; i <temp.length ; i++) {
            countArray[temp[i]-min]++;
        }
        //遍历计数数组 输出排序后的数组
        int index=0;
        int[] result = new int[temp.length];
        for (int i = 0; i < countArray.length ; i++) {
            for (int j = 0; j <countArray[i] ; j++) {
                result[index++] = i + min;
            }
        }
        return result;
    }

    public static int[] countSortUpper(int[] arrays) {
        //先取出最大值
        if (arrays.length == 0) {
            return arrays;
        }
        int max=arrays[0];
        int min=arrays[0];
        for (int i = 1; i <arrays.length; i++) {
            if (arrays[i] > max) {
                max = arrays[i];
            }
            if (arrays[i] < min) {
                min = arrays[i];
            }
        }
        //初始化计数数组
        int[] countArray = new int[max-min+1];
        for (int i = 0; i <arrays.length ; i++) {
            countArray[arrays[i]-min]++;
        }

        //计数数组做变形 存储值：后面元素为前面元素之和
        int sum = 0;
        for (int i = 0; i <countArray.length ; i++) {
            sum += countArray[i];
            countArray[i] = sum;
        }
        //遍历计数数组 输出排序后的数组
        int[] result = new int[arrays.length];
        for (int i = arrays.length-1 ; i >=0 ; i--) {
            //原数组在计数数组对应位置
            int sign = arrays[i]-min;
            result[countArray[sign]-1] = arrays[i];
            countArray[sign]--;
        }
        return result;
    }



}
