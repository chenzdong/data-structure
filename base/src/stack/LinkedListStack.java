package stack;

import java.util.Arrays;

/**
 * 链表实现栈
 *
 * @author: czd
 * @create: 2019-11-05 15:24
 */
public class LinkedListStack {
    private Node top = null;
    public void push(String value) {
        Node node = new Node(null, value);
        if (top == null) {
            top = node;
        } else {
            node.next = top;
            top = node;
        }
    }
    public String pop() {
        if (top == null) {
            return null;
        }
        String value = top.data;
        top = top.next;
        return value;
    }
    public void printAll() {
        Node p = top;
        while (p != null) {
            System.out.println(p.getData());
            p = p.next;
        }
    }
    private static class Node {
        private Node next;
        private String data;

        public Node(Node next, String data) {
            this.next = next;
            this.data = data;
        }
        public String getData() {
            return this.data;
        }
    }

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        stack.push("test");
        stack.push("czd");
        stack.push("com");
        System.out.println(stack.pop());
        stack.push("xixi");
        System.out.println(stack.pop());
        stack.printAll();
    }
}
