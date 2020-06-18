package stack;

import java.util.Stack;

/**
 * @author: czd
 * @create: 2020-06-11 14:00
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> min_stack;
    public MinStack(int capacity) {
        stack = new Stack<>();
        min_stack = new Stack<>();
    }
    public void push(int value) {
        stack.push(value);
        if (min_stack.isEmpty() || min_stack.peek() < value) {
            min_stack.push(value);
        }
    }
    public int pop() {
        if (stack.isEmpty()) {
            return -1;
        }
        int value = stack.pop();
        if (min_stack.peek() == value) {
            min_stack.pop();
        }
        return value;
    }
    public int getMin() {
        if (min_stack.isEmpty()) {
            return -1;
        }
        return min_stack.pop();
    }
}
