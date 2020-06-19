package dynamicProgramming;

/**
 * 硬币问题
 * 我们有 3 种不同的硬币，1 元、3 元、5 元，
 * 我们要支付 9 元，最少需要 3 个硬币（3 个 3 元的硬币
 * 状态转移方程 solve(n) = 1 + Math.min(solve(n-1), solve(n-3), min(solve(n-5))
 *
 * @author: czd
 * @create: 2020-06-19 10:33
 */
public class Coin {
    /**
     * 递归
     *
     * @param value
     * @return
     */
    private static int[] mem;
    private static int count;

    public static int getCount(int value) {
        if (mem[value] >= 0) {
            return mem[value];
        }
        if (value < 1) {
            return Integer.MAX_VALUE;
        }
        if (value == 1) {
            return 1;
        }
        if (value == 3) {
            return 1;
        }
        if (value == 5) {
            return 1;
        }
        count++;
        int min = 1 + Math.min(getCount(value - 1), Math.min(getCount(value - 3), getCount(value - 5)));
        mem[value] = min;
        return min;
    }

    public static int getCountDP(int value) {
        return 0;
    }

    public static void main(String[] args) {
        int n = 9;
        mem = new int[n + 1];
        System.out.println(getCount(9));
        System.out.println(count);
    }
}
