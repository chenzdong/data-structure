package backtrack;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 八皇后问题
 *
 * @author: czd
 * @create: 2020-04-23 14:51
 */
public class EightQueen {
    /**
     * 存储结果 result[i][j]代表第i行放在第j个位置
      */
    int[] result = new int[8];
    public  static AtomicInteger count = new AtomicInteger();

    /**
     *
     * @param row
     */
    public void callEightQueen(int row) {
        // 终止条件:如果满足条件了就直接返回，这里是八个棋子都放置好了
        if (row == 8) {
            printQueens(result);
            return;
        }
        for (int column = 0; column < 8; column++) {
            if (isOk(row, column)) {
                result[row] = column;
                callEightQueen(row+1);
            }
        }
    }

    /**
     * 判断是否符合规则
     * @param row
     * @param column
     * @return
     */
    private boolean isOk(int row, int column) {
        int leftUp = column - 1;
        int rightUp = column + 1;
        // 逐行向上考察
        for (int i = row - 1; i >= 0; i--) {
            // 不同行同一列有棋子了,不符合规则
            if (result[i] == column) {
                return false;
            }
            // 考察左上对角线
            if (leftUp >= 0) {
                if (result[i] == leftUp) {
                    return false;
                }
            }
            --leftUp;
            // 考察右上对角线
            if (rightUp < 8) {
                if (result[i] == rightUp) {
                    return false;
                }
            }
            ++rightUp;
        }
        return true;
    }

    /**
     * 打印结果
     * @param result
     */
    private void printQueens(int[] result) {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
        count.incrementAndGet();
    }

    public static void main(String[] args) {
        EightQueen queen = new EightQueen();
        queen.callEightQueen(0);
        System.out.println(count);
    }
}
