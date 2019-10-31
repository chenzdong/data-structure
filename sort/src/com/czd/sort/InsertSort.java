package com.czd.sort;

/**
 * 插入排序
 * 最好 O(n) 最坏O(n2) 平均O(n2)
 * 稳定排序
 * 原地排序
 * @author: czd
 * @create: 2018/4/23 10:11
 */
public class InsertSort {
    /**
     * 插入排序 每一轮每个位置插入对应的值
     * 一共n-1轮 从i+1->0为止 使其插入相应位置
     * @param args
     */
    public static void main(String[] args) {
        int[] numbers={1,5,87,69,75,2,7,46,6};
        insertSort(numbers);
    }
    private static void insertSort(int[] numbers) {
        int count = numbers.length;
        if (count <= 1) {
            return;
        }
        // 第n轮将第n个位置的值插入到之前排序好的n-1区间中
        for (int i = 1; i < count; i++) {
            int value = numbers[i];
            int j = i-1;
            for (; j > 0; j--) {
                if (numbers[j] > value) {
                    numbers[j+1] = numbers[j];
                } else {
                    break;
                }
            }
            numbers[j+1] = value;

        }
        for (int number : numbers) {
            System.out.println(number+",");
        }
    }
    private static void commonInsertSort(int[] numbers){
        int size=numbers.length;
        int count=0;
        for (int i = 0; i <size-1 ; i++) {
            int j=i+1;
            while(j>0 && numbers[j]<numbers[j-1]){
                count++;
                int temp=numbers[j-1];
                numbers[j-1]=numbers[j];
                numbers[j]=temp;
                j--;
            }
        }
        for (int i = 0; i <size; i++) {
            System.out.println(numbers[i]);
        }
        System.out.println("count is " + count);
    }
    private static  void commonInsertSort1(int[] numbers){
        int size=numbers.length;
        int count=0;
        for (int i = 0; i <size; i++) {
            //待插入的值
            int key=numbers[i];
            int position=i;
            while(position>0 && numbers[position-1]>key){
                count++;
                numbers[position]=numbers[position-1];
                position--;
            }
            numbers[position]=key;
        }
        for (int i = 0; i <size; i++) {
            System.out.println(numbers[i]);
        }
        System.out.println("count is " + count);
    }
}
