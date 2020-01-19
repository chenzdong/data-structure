package com.czd.bm;

/**
 * 字符串匹配BM算法
 *
 * @author: czd
 * @create: 2020-01-17 10:24
 */
public class BM {
    /**
     * 数据长度 ascii码长度一个字节 2^8=256
     */
    private static final int SIZE = 256;

    /**
     * 将模式串的位置对应到相关位置 bc[ascii] = index
     * @param b 模式串
     * @param m 模式串长度
     * @param bc 哈希表
     */
    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            int ascii = (int)b[i];
            bc[ascii] = i;
        }
    }

    /**
     * 找出对应位置 坏字符规则
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return 对应位置
     */
    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE];
        // 坏字符规则
        generateBC(b, m, bc);
        int i = 0;
        while (i <= n - m) {
            int j;
            // 模式串从后往前匹配
            for (j = m - 1; j >= 0; --j) {
                // 不匹配直接终止
                if (a[i+j] != b[j]) {
                    break;
                }
            }
            // 找到就直接返回
            if ( j < 0) {
                return i;
            }
            i = i + (j - bc[(int)a[i+j]]);
        }
        return -1;
    }

    /**
     * 生成好后缀规则的prefix和suffix数组
     * @param b 模式串
     * @param m 模式串长度
     * @param suffix
     * @param prefix
     */
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        // 初始化数组
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        for (int i = 0; i < m - 1; i++) {
            int j = i;
            // 公共后缀子串长度
            int k = 0;
            // 与b[0, m-1]求公共后缀子串
            while (j >= 0 && b[j] == b[m-1-k]) {
                --j;
                ++k;
                //j+1表示公共后缀子串在b[0, i]中的起始下标
                suffix[k] = j + 1;
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    /**
     * 坏字符规则+好后缀规则
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return
     * 整个算法用到了额外的 3 个数组，
     * 其中 bc 数组的大小跟字符集大小有关，suffix 数组和 prefix 数组的大小跟模式串长度 m 有关
     * 最差 O(m^2)
     * O(3n)
     */
    public int bmUpper(char[] a, int n, char[] b, int m) {
        // 坏字符规则
        int[] bc = new int[SIZE];
        generateBC(b, m, bc);
        // 好后缀规则
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        int i = 0;
        while (i <= n - m) {
            // 坏字符规则
            int j;
            // 模式串从后往前匹配
            for (j = m - 1; j >= 0; --j) {
                // 不匹配直接跳过
                if (a[i+j] != b[j]) {
                    break;
                }
            }
            // 找到就直接返回
            if ( j < 0) {
                return i;
            }
            int x = i + (j - bc[(int)a[i+j]]);
            int y = 0;
            // 如果有好后缀
            if (j < m - 1) {
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    /**
     * 返回好后缀规则移动距离
     * @param j 坏字符对应的模式串的字符下标
     * @param m 模式串长度
     * @param suffix
     * @param prefix
     * @return
     */
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        // 好后缀长度
        int k = m - 1- j;
        // 模式串存在与好后缀一样的字符串
        if (suffix[k] != -1) {
            return j - suffix[k] + 1;
        }
        // 模式串不存在与好后缀一样的字符串
        for (int r = j + 2; r < m - 1; ++r) {
            if (prefix[m-r] == true) {
                return r;
            }

        }
        return m;
    }
}
