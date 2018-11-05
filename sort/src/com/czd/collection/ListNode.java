package com.czd.collection;

import lombok.Data;

/**
 * @author: czd
 * @create: 2018/7/13 9:20
 */
@Data
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
