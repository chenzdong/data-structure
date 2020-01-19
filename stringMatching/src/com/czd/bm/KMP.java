package com.czd.bm;

/**
 * 字符串匹配算法KMP
 *
 * @author: czd
 * @create: 2020-01-19 10:11
 */
public class KMP {
    /**
     * 字符串匹配KMP算法
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return
     * 空间复杂度 O(m)
     * 时间复杂度 O(m+n)
     */
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNext(b, m);
        // 坏字符所在位置
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && a[i] != b[j]) {
                // 遇到不匹配的时候向后移，移动位数和next数组值有关
                j = next[j-1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            // 模式匹配
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    /**
     * 返回next数组
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    private static int[] getNext(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 0; i < m; i++) {
            while (k != -1 && b[k+1] != b[i]) {
                k = next[k];
            }
            if (b[k+1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }



}
