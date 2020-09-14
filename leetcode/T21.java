package com.project.jvm.leetcode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class T21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;

        if(l1.val>l2.val) {
            head = l2;
            l2 = l2.next;
        }else {
            head = l1;
            l1 = l1.next;
        }
        ListNode ret = head;

        while(l1.next!=null && l2.next!=null) {
            if(l1.val < l2.val) {
                head.next = l1;
                l1 = l1.next;
                head = head.next;
            }else {
                head.next = l2;
                l2 = l2.next;
                head = head.next;
            }
        }

        while(l1.next != null) {
            head.next = l1;
            l1 = l1.next;
            head = head.next;
        }

        while (l2.next != null) {
            head.next = l2;
            l2 = l2.next;
            head = head.next;
        }

        return ret;
    }

}