package com.czd.collection;

import com.czd.collection.ListNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: czd
 * @create: 2018/7/13 9:20
 */
public class Solution {
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(4);
        node.next = new ListNode(5);
        node.next = new ListNode(1);
        node.next = new ListNode(9);
        deleteNode(node);
        System.out.println(node.val);
    }
}
