package collection;

import java.util.Stack;

/**
 * 通过两个栈构建一个queue
 *
 * @author: czd
 * @create: 2018/10/22 13:41
 */
public class QueueByStack {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();
    //入栈
    public void enQueue(int element) {
        stack1.push(element);
    }

    //出栈
    public Integer deQueue() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
               return null;
            }
            //填入栈
            transfer();
        }
        return stack2.pop();
    }

    public void transfer() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
    public static void main(String[] args) {
        QueueByStack queueByStack = new QueueByStack();
        queueByStack.enQueue(1);
        queueByStack.enQueue(2);
        queueByStack.enQueue(3);
        System.out.println(queueByStack.deQueue());
        System.out.println(queueByStack.deQueue());
        queueByStack.enQueue(4);
        System.out.println(queueByStack.deQueue());
        System.out.println(queueByStack.deQueue());
    }
}
