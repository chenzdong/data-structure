package com.czd.collection;

/**
 * 交换两个变量的值
 *
 * @author: czd
 * @create: 2019/3/22 10:03
 */
public class Swap {
    public static void main(String[] args) {
        int a = 0;
        int b = 5;
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a:"+a+" b:"+b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a:"+a+" b:"+b);
    }
}
