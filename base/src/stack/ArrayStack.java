package stack;

import java.util.Arrays;

/**
 * 数组实现栈
 *
 * @author: czd
 * @create: 2019-11-05 14:57
 */
public class ArrayStack {
    private String[] items;
    private int size;
    private int count;

    public ArrayStack(int size) {
        this.items = new String[size];
        this.size = size;
        this.count = 0;
    }

    /**
     * 入栈操作
      * @param item
     * @return
     */
    public boolean push(String item) {
        if (size == count) {
            return false;
        }
        items[count] = item;
        count++;
        return true;
    }

    /**
     * 出栈操作
     * @return
     */
    public String pop() {
        if (count == 0 ) {
            return null;
        }
        String item = items[count-1];
        count--;
        return item;
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(3);
        stack.push("test");
        stack.push("czd");
        stack.push("com");
        System.out.println(stack.push("hello"));
        System.out.println(stack.pop());
        stack.push("xixi");
        System.out.println(stack.pop());
        System.out.println(Arrays.toString(stack.items));
        System.out.println(stack.count);
    }
}
