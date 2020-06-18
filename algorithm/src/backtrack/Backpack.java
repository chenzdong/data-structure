package backtrack;

/**
 * 背包问题
 * 我们有一个背包，背包总的承载重量是 Wkg。
 * 现在我们有 n 个物品，每个物品的重量不等，并且不可分割。
 * 我们现在期望选择几件物品，装载到背包中。
 * 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
 * 思路：物品依次排列，整个问题就分解为了 n 个阶段，每个阶段对应一个物品怎么选择。
 * 先对第一个物品进行处理，选择装进去或者不装进去，然后再递归地处理剩下的物品
 * 时间复杂度O(2^n)
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

    /**
     * 背包问题:求最大重量
     * @param i 考察到第几个物品了
     * @param cw 当前已经装进去的每个物品的重量
     */
    public static void pack(int i, int cw) {
        boolean[][] access = new boolean[n][w+1];
        // 终止条件:cw == w表示装满了 i==n表示已经考察完所有的物品
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        if (access[i][cw] == true) {
            return;
        }
        access[i][cw] = true;
        // 不装当前物品的情况
        pack(i+1, cw);
        // 装当前物品的情况
        // 判断是否超过背包重量
        if (cw + items[i] <= w) {
            pack(i+1, cw + items[i]);
        }
    }
    /**
     * 背包问题：求最大价值
     * @param i 考察到第几个物品了
     * @param cw 当前已经装进去的重量
     * @param cv 当前以及装进去的价值
     */
    public static void packValue(int i, int cw, int cv) {
        if (cw == w || i == n) {
            if (cv > maxV) {
                maxV = cv;
            }
            return;
        }
        // 不装当前
        packValue(i+1, cw, cv);
        // 装入当前
        if (cw + items[i] <= w) {
            packValue(i+1, cw+items[i], cv+value[i]);
        }
    }
    /**
     * 背包问题
     * @param weight 背包中各个物品重量
     * @param n 物品个数
     * @param w 背包可承载重量
     * @return 最大重量
     */
    public static int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w+1];
        // 第一行需要特殊处理
        states[0][0] = true;
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                if (states[i-1][j] == true) {
                    // 不把第i个物品放入背包
                    states[i][j] = states[i-1][j];
                }
            }
            for (int j = 0; j <= w - weight[i]; j++) {
                // 把第i个物品放入背包
                if (states[i-1][j] == true) {
                    states[i][j+weight[i]] = true;
                }
            }
        }
        // 倒序遍历最后一行，取出最大的那个重量
        for (int i = w; i >= 0; i--) {
            if (states[n-1][i] == true) {
                return i;
            }
        }
        return 0;
    }
    public static int knapsack3(int[] items, int[] value, int n, int w) {
        int[][] states = new int[n][w+1];
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
            // 不装当前物品
            for (int j = 0; j <= w; j++) {
                if (states[i-1][j] >= 0) {
                    states[i][j] = states[i-1][j];
                }
            }
            // 装入当前物品
            for (int j = 0; j <= w - items[i]; j++) {
                if (states[i-1][j] >= 0) {
                    int v = states[i-1][j] + value[i];
                    if (v > states[i][j+items[i]]) {
                        states[i][j + items[i]] = states[i - 1][j] + value[i];
                    }
                }
            }
        }
        int max = -1;
        for (int i = 0; i <= w; i++) {
            if (states[n-1][i] > max) {
                max = states[n-1][i];
            }
        }
        return max;
    }

    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w+1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w-items[i]; j >= 0; --j) {//把第i个物品放入背包，从小到大会重复
                if (states[j]==true) {
                    states[j+items[i]] = true;
                };
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
        // todo 顺序对结果的影响
        items = new int[]{2,2,2,2,4,3,9,1};
        value = new int[]{3,5,8,7,4,2,5,1};
        pack(0,0);
        System.out.println(knapsack2(items, 5, 25));
        System.out.println(maxW);
//        packValue(0,0,0);
//        System.out.println(maxV);
//        System.out.println(knapsack3(items, value, 5, 25));
    }
}
