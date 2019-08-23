package com.czd.sort;

/**
 * 冒泡排序
 *
 * @author: czd
 * @create: 2018/4/20 9:46
 */
public class BubbleSort {
    /**
     * 冒泡排序 每次选出最大的放到最后 相邻位置比较
     * 一共n-1轮 每轮需要n-i-1次比较与交换
     */
    public static void main(String[] args) {
        int[] numbers={2,36,56,42,58,69,87,89,99};
//        commonBubbleSort(numbers);
//        upperBubbleSort1(numbers);
//        upperBubbleSort2(numbers);
        cockTailSort(numbers);
    }

   /**
    * 普通的冒泡排序
    */
    private static void commonBubbleSort(int[] numbers) {
        int n = numbers.length;
        int count = 0;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j <n-1-i; j++) {
                count++;
                if (numbers[j] > numbers[j+1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
        System.out.println("普通的冒泡排序:");
        for (int i = 0; i <n; i++) {
            System.out.println(numbers[i]);
        }
        System.out.println("count is " + count);
    }
    /**
     * 优化冒泡排序 （未发生交换，就直接停止）
     */
    private static void upperBubbleSort1(int[] numbers){
        int n=numbers.length;
        int count = 0;
        for (int i = 0; i < n-1; i++) {
            boolean flag = false;
            for (int j = 0; j <n-1-i; j++) {
                count++;
                if (numbers[j] > numbers[j+1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        System.out.println("优化的冒泡排序1:");
        for (int i = 0; i <n ; i++) {
            System.out.println(numbers[i]);
        }
        System.out.println("count is "+count);
    }
    /**
     * 优化冒泡排序 （未发生交换，就直接停止；优化内部循环次数）
     */
    private static void upperBubbleSort2(int[] numbers){
        int n = numbers.length;
        int count = 0;
        //记录已经排序好的下标值，下次直接从这个下标开始比较
        int k = n-1;
        for (int i = 0; i < n-1; i++) {
            boolean flag = false;
            int min = 0;
            for (int j = 0; j < k; j++) {
                count++;
                if (numbers[j] > numbers[j+1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                    flag = true;
                    min = j;
                }
            }
            if (!flag) {
                break;
            }
            k = min;
        }
        System.out.println("优化的冒泡排序2:");
        for (int i = 0; i <n ; i++) {
            System.out.println(numbers[i]);
        }
        System.out.println("count is "+count);
    }
    /**
     * 鸡尾酒排序 （奇数轮从左到右 偶数轮从右到左 适用于基本有序的数组）
     */
    public static void cockTailSort(int[] numbers) {
        int count = 0;
        int length = numbers.length;
        int lastRightIndex = 0;
        int lastLeftIndex = 0;
        int rightBorder = length-1;
        int leftBorder = 0;
        for (int i = 0; i <length/2; i++) {
            boolean flag = false;
            //奇数轮从右到左
            for (int j = 0; j <rightBorder ; j++) {
                count++;
                if (numbers[j] > numbers[j+1]){
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                    flag = true;
                    lastRightIndex = j;
                }
            }
            rightBorder = lastRightIndex;
            if (!flag) {
                break;
            }
            flag = false;
            //偶数轮从左到右
            for (int j = length-1-i ; j > leftBorder ; j--) {
                count++;
                if (numbers[j] < numbers[j-1]){
                    int temp = numbers[j];
                    numbers[j] = numbers[j-1];
                    numbers[j-1] = temp;
                    flag = true;
                    lastLeftIndex = j;
                }
            }
            leftBorder = lastLeftIndex;
            if (!flag) {
                break;
            }
        }
        System.out.println("count is "+count);
    }
}
