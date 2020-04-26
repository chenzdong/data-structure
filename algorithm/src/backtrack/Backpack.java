package backtrack;

import java.util.Arrays;

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
    public int maxW = Integer.MIN_VALUE;

    public int n = 5;
    public int w = 25;

    /**
     * 背包问题
     * @param i 考察到第几个物品了
     * @param cw 当前已经装进去的每个物品的重量
     * @param items 每个物品的重量
     */
    public void pack(int i, int cw, int[] items) {
        // 终止条件:cw == w表示装满了 i==n表示已经考察完所有的物品
        if (cw == w || i == n) {
            if (cw > maxW) {
                System.out.println(i+":"+cw);
                maxW = cw;
            }
            return;
        }
        // 不装当前物品的情况
        pack(i+1, cw, items);
        // 装当前物品的情况
        // 判断是否超过背包重量
        if (cw + items[i] <= w) {
            pack(i+1, cw + items[i], items);
        }
    }

    public static void main(String[] args) {
        Backpack backpack = new Backpack();
        // todo 顺序对结果的影响
        int[] items = new int[]{9,2,8,5,4,3,9,1};
        backpack.pack(0,0, items);
        System.out.println(backpack.maxW);
    }
}
