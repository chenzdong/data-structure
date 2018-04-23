package com.czd.sort;

/**
 * 选择排序
 *
 * @author: czd
 * @create: 2018/4/20 14:16
 */
public class ChoseSort {
    /**
     *选择排序 一共n轮 每轮所需位置与其他位置相比
     * 第i轮与i+1位置开始比较
     */
    public static void main(String[] args) {
        int[] number={49,38,65,76,13,27,10,97,98};
        upperChoseSort2(number);
    }
    private  static  void commonChoseSort(int[] numbers){
        int n=numbers.length;
        int count=0;
        for(int i=0;i<n-1;i++){
            for (int j = i+1; j <n ; j++) {
                count++;
                if(numbers[i]>numbers[j]){
                    int temp=numbers[i];
                    numbers[i]=numbers[j];
                    numbers[j]=temp;

                }
            }
        }
        for (int i = 0; i <n ; i++) {
            System.out.println(numbers[i]);
        }
        System.out.println("count is:" + count);
    }

    /**
     * 优化选择排序 （未发生交换，排序完成）
     * @param numbers
     */
    private  static  void upperChoseSort1(int[] numbers){
        int n=numbers.length;
        int count=0;
        for(int i=0;i<n-1;i++){
            boolean flag=true;
            for (int j = i+1; j <n ; j++) {
                count++;
                if(numbers[i]>numbers[j]){
                    int temp=numbers[i];
                    numbers[i]=numbers[j];
                    numbers[j]=temp;
                    flag=false;
                }
            }
            if (flag){
                break;
            }
        }
        for (int i = 0; i <n ; i++) {
            System.out.println(numbers[i]);
        }
        System.out.println("count is:"+count);
    }
    /**
     * 优化选择排序 （每轮确定最小值位置 与之交换 时间复杂度不变）
     * @param numbers
     */
    private  static  void upperChoseSort2(int[] numbers){
        int n=numbers.length;
        int count=0;
        for(int i=0;i<n-1;i++){
            //保证最小值 每轮该位置与最小值交换
            int min=i;
            for (int j = i+1; j <n ; j++) {
                count++;
                if(numbers[min]>numbers[j]){
                    min=j;
                }
            }
            int temp=numbers[i];
            numbers[i]=numbers[min];
            numbers[min]=temp;
        }
        for (int i = 0; i <n ; i++) {
            System.out.println(numbers[i]);
        }
        System.out.println("count is:"+count);
    }
}
