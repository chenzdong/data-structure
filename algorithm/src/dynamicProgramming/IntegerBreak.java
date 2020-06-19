package dynamicProgramming;

/**
 * 剪绳子
 * 长度为N的绳子，剪M刀，使得乘积最大
 * 转移表达式 res = Math.max(res, Math.max(i*f(n-i), i*(n-i)))
 * @author: czd
 * @create: 2020-06-19 14:25
 */
public class IntegerBreak {
    /**
     * 递归+备忘录
     * @param n
     * @return
     */
    private static int[] mem;
    public static int solve(int n) {
        if (mem[n] > 0) {
            return mem[n];
        }
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int res = 0;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j < i - 1;j++) {
                res = Math.max(res, Math.max(j * solve(n-j), j * (n-j)));
            }
        }
        mem[n] = res;
        return res;
    }

    /**
     * 数学方法
     * @param n
     * @return
     */
    public static int solve1(int n) {
        int a = n % 3;
        int b = n / 3;
        if (a == 0) {
            return (int)Math.pow(3, b);
        }
        if (a == 1) {
            return (int)Math.pow(3, b-1) * 4;
        }
        if (a == 2) {
            return (int)Math.pow(3, b) * 2;
        }
        return 0;
    }

    /**
     * 动态规划
     * @return
     */
    public static int solveDP(int n) {
        if (n < 2) {
            return 0;
        }
        int[] dp = new int[n+1];
        dp[2] = 1;
        for (int i = 3 ; i <= n; i++) {
            for (int j = 1; j <= i/2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * solveDP(n-j), j * (n-j)));
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        int n = 10;
        mem = new int[n+1];
        System.out.println(solve(10));
        System.out.println(solve1(10));
        System.out.println(solveDP(10));
    }
}
