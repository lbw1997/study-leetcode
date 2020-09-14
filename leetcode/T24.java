package com.project.jvm.leetcode;

import java.util.List;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class T24 {

    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode cur = head;
        ListNode next = head.next;

        while (cur.next.next != null) {
            cur = cur.next;
            next = cur.next;

            pre.next = next;
            cur.next = next.next;
            next.next = cur;


            pre = cur;

        }

        return dummy.next;
    }
}
