package com.project.jvm.leetcode;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(l1.val+l2.val);
        while(l1.next!=null&&l2.next!=null) {
            result.next = new ListNode(l1.next.val+l2.next.val);
        }
        if (l1.next!=null)
            result.next = new ListNode(l1.next.val);
        if (l2.next!=null)
            result.next = new ListNode(l2.next.val);
        return result;
    }
    public static void main(String[] args) {
        AddTwoNumbers a = new AddTwoNumbers();
        ListNode l1 = new ListNode(2);

    }
}
