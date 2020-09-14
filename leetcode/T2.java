package com.project.jvm.leetcode;

import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class T2 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int sum = l1.val + l2.val;
        int c = sum/10;
        ListNode head = new ListNode(sum%10);
        ListNode result = head;

        while (l1.next !=null && l2.next !=null) {
            sum = l1.next.val + l2.next.val + c;
            c = sum/10;
            head.next = new ListNode(sum%10);
            head = head.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1.next != null) {
            sum = l1.next.val + c;
            c = sum / 10;
            head.next = new ListNode(sum%10);
            head = head.next;
            l1 = l1.next;
        }

        while (l2.next != null) {
            sum = l2.next.val + c;
            c = sum / 10;
            head.next = new ListNode(sum%10);
            head = head.next;
            l2 = l2.next;
        }

        if (c!=0) {
            head.next = new ListNode(c);
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);

        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode!=null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

