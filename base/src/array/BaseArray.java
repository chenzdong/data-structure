package array;

import java.util.Arrays;

/**
 * 数组基础
 *
 * @author: czd
 * @create: 2019/3/29 14:20
 */
public class BaseArray {
    public static void main(String[] args) {
        int[] a = new int[5];
        int[] b = {1,2,3};
        // 复制的是地址
        a = b;
        // 3
        System.out.println("the size of a is :"+ a.length);
        // 1
        System.out.println("the first element of a is :"+ a[0]);
        // 1 2 3
        for (int i = 0; i < b.length; i++) {
            System.out.println("b is");
            System.out.println(b[i]);
        }
        b[0] = 4;
        a[1] = 5;
        // 4 5 3
        for (int i : b) {
            System.out.println("b 2 is");
            System.out.println(i);
        }
        // 4 5 3
        for (int i : a) {
            System.out.println("a is");
            System.out.println(i);
        }
//        3 4 5
        Arrays.sort(b);
        for (int i : b) {
            System.out.println("b 3 is");
            System.out.println(i);
        }
    }
}
