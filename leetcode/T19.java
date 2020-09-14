package com.project.jvm.leetcode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 */
public class T19 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {


        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 1;i<=n+1;i++) {
            fast = fast.next;
        }
        while (fast!=null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        ListNode head = new ListNode(nums[0]);
        ListNode cur = head;

        for (int i = 1;i<nums.length;i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }

        head = removeNthFromEnd(head,1);

        while (head!=null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
