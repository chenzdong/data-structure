package backtrack;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 背包问题
 * 我们有一个背包，背包总的承载重量是 Wkg。
 * 现在我们有 n 个物品，每个物品的重量不等，并且不可分割。
 * 我们现在期望选择几件物品，装载到背包中。
 * 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
 * 思路：物品依次排列，整个问题就分解为了 n 个阶段，每个阶段对应一个物品怎么选择。
 * 先对第一个物品进行处理，选择装进去或者不装进去，然后再递归地处理剩下的物品
 * 时间复杂度O(2^n)
 * state[i][cw] = state[i-1][cw]
 * state[i][cw] = state[i][cw+item[i]]
 *
 * @author: czd
 * @create: 2020-04-23 15:44
 */
public class Backpack {
    /**
     * 当前背包最大重量
     */
    public static int maxW = Integer.MIN_VALUE;
    private static int[] items;
    public static int maxV = Integer.MIN_VALUE;
    private static int[] value;
    public static int n = 5;
    public static int w = 25;
    static boolean[][] mem = new boolean[n + 1][w + 1];
    static AtomicInteger count = new AtomicInteger(0);

    /**
     * 背包问题:求最大重量
     *
     * @param i  考察到第几个物品了
     * @param cw 当前已经装进去的每个物品的重量
     */
    public static void pack(int i, int cw) {
        if (mem[i][cw]) {
            return;
        }
        if (i == n || cw == w) {
            if (maxW < cw) {
                maxW = cw;
            }
            return;
        }
        count.getAndIncrement();
        mem[i][cw] = true;
        // 不装当前的物品
        pack(i + 1, cw);
        // 装入当前的物品
        if (cw + items[i] <= w) {
            pack(i + 1, cw + items[i]);
        }
    }

    /**
     * 背包问题：求最大价值
     *
     * @param i  考察到第几个物品了
     * @param cw 当前已经装进去的重量
     * @param cv 当前以及装进去的价值
     */
    public static void packValue(int i, int cw, int cv) {
        if (i == n || cw == w) {
            if (maxV < cv) {
                maxV = cv;
            }
            return;
        }
        // 不装当前物品
        packValue(i, cw, cv);
        if (cw + items[i] <= w) {
            packValue(i, cw + items[i], cv + value[i]);
        }
    }

    /**
     * 背包问题
     *
     * @param weight 背包中各个物品重量
     * @param n      物品个数
     * @param w      背包可承载重量
     * @return 最大重量
     */
    public static int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1];
        states[0][0] = true;
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            // 不装当前物品
            for (int j = 0; j <= w; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = true;
                }
            }
            // 装入当前物品
            for (int j = 0; j <= w - weight[i]; j++) {
                if (states[i - 1][j]) {
                    states[i][j + weight[i]] = true;
                }
            }
        }
        // 输出最后一行 i = n-1,j最大的数据
        for (int i = n; i >= 0; i--) {
            if (states[n - 1][i]) {
                return i;
            }
        }
        return 0;

    }

    public static int knapsack3(int[] items, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1];
        // 初始化
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < w + 1; j++) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (items[0] <= w) {
            states[0][items[0]] = value[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < w + 1; j++) {
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j + items[i] <= w + 1; j++) {
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + items[i]]) {
                        states[i][j + items[i]] = v;
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < w + 1; i++) {
            if (max < states[n - 1][i]) {
                max = states[n - 1][i];
            }
        }
        return max;

    }

    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - items[i]; j >= 0; --j) {//把第i个物品放入背包，从小到大会重复
                if (states[j] == true) {
                    states[j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        items = new int[]{2, 2, 2, 2, 4, 3, 9, 1};
        value = new int[]{3, 5, 8, 7, 4, 2, 5, 1};
        pack(0, 0);
        System.out.println(knapsack2(items, 5, 25));
        System.out.println(maxW);
        System.out.println(count);
//        packValue(0,0,0);
//        System.out.println(maxV);
//        System.out.println(knapsack3(items, value, 5, 25));
    }
}
