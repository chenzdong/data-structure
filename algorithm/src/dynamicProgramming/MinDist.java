package dynamicProgramming;

/**
 * 矩阵路径问题
 * 假设我们有一个 n 乘以 n 的矩阵 w[n][n]。矩阵存储的都是正整数。
 * 棋子起始位置在左上角，终止位置在右下角。我们将棋子从左上角移动到右下角。
 * 每次只能向右或者向下移动一位。从左上角到右下角，会有很多不同的路径可以走。
 * 我们把每条路径经过的数字加起来看作路径的长度。那从左上角移动到右下角的最短路径长度是多少呢
 * min_dist(i, j) = w[i][j] + Math.min(min_dist(i-1, j), min_dist(i, j-1))
 *
 * @author: czd
 * @create: 2020-06-19 10:05
 */
public class MinDist {
    private int minDist = Integer.MAX_VALUE;
    private int[][] mem = new int[5][5];

    /**
     * 回溯算法
     *
     * @param w    矩阵
     * @param n    矩阵大小
     * @param i    行
     * @param j    列
     * @param dist 当前路径
     */
    public void minDistBT(int[][] w, int n, int i, int j, int dist) {
        if (i == n - 1 && j == n - 1) {
            dist = dist + w[i][j];
            if (minDist > dist) {
                minDist = dist;
            }
            return;
        }
        if (i < n - 1) {
            minDistBT(w, n, i + 1, j, dist + w[i][j]);
        }
        if (j < n - 1) {
            minDistBT(w, n, i, j + 1, dist + w[i][j]);
        }
    }

    /**
     * 递归+备忘录
     *
     * @param w
     * @param n
     * @param i
     * @param j
     * @return
     */
    public int minDist(int[][] w, int n, int i, int j) {
        if (i == 0 && j == 0) {
            return w[0][0];
        }
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(w, n, i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(w, n, i - 1, j);
        }
        int curMin = w[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = curMin;
        return curMin;
    }

    /**
     * 动态规划
     *
     * @param w
     * @param n
     * @return
     */
    public int minDistDP(int[][] w, int n) {
        int[][] result = new int[n][n];
        // 初始化
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = -1;
            }
        }
        // 初始化第一行
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += w[0][i];
            result[0][i] = sum;
        }
        // 初始化第一列
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += w[i][0];
            result[i][0] = sum;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = w[i][j] + Math.min(result[i - 1][j], result[i][j - 1]);
            }
        }
        return result[n - 1][n - 1];
    }

    public static void main(String[] args) {
        MinDist dist = new MinDist();
        int[][] matrix = new int[][]{{1, 6, 5, 9, 1}, {2, 1, 7, 4, 2}, {5, 5, 6, 7, 2}, {6, 8, 4, 8, 1}, {1, 2, 1, 2, 2}};
        dist.minDistBT(matrix, 5, 0, 0, 0);
        System.out.println(dist.minDist);
        System.out.println(dist.minDist(matrix, 5, 4, 4));
        System.out.println(dist.minDistDP(matrix, 5));
    }
}
