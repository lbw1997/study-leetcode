package com.project.jvm.leetcode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class T23 {

    private ListNode mergeTwoLists(ListNode head, ListNode list) {

        if (head == null || list == null) {
            return (head == null)?list:head;
        }

        if (head.val<=list.val) {
            head.next = mergeTwoLists(head.next,list);
            return head;
        }else {
            list.next = mergeTwoLists(head,list.next);
            return list;
        }
    }

    private ListNode helper(ListNode[] lists, int begin, int end) {

        if (begin == end) return lists[begin];

        int mid = begin + (end - begin) / 2;

        ListNode left = helper(lists,begin,mid);
        ListNode right = helper(lists,mid+1,end);
        return mergeTwoLists(left,right);

    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        return helper(lists,0,lists.length-1);

        //解3、使用两两合并的方式，把k个list合并到一起
//        if (lists == null || lists.length == 0) return null;

//        ListNode head = lists[0];
//        for (int i = 1;i<lists.length;i++) {
//            head = mergeTwoLists(head,lists[i]);
//        }
//        return head;

        //解2、堆排序，使用优先队列按从小到大存放节点，之后只需要按顺序读取即可
//        PriorityQueue<ListNode> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o.val)));
//
//        for (ListNode listNode : lists) {
//            if (listNode!=null) {
//                pq.add(listNode);
//            }
//        }
//
//        ListNode dummy = new ListNode(0);
//        ListNode tail = dummy;
//
//        while (!pq.isEmpty()) {
//            ListNode node = pq.poll();
//            tail.next = node;
//            tail = tail.next;
//            if (node.next!=null) {
//                pq.add(node.next);
//            }
//        }
//
//        tail.next = null;
//        return dummy.next;


        //解1、遍历ListNode数组，找到最小节点以及所在数组的下标，把最小节点添加到结果节点上，之后移动节点位置。
//        if (lists == null || lists.length == 0) return null;
//
//        ListNode dummy = new ListNode(0);
//        ListNode tail = dummy;
//
//        for (;;) {
//
//            ListNode minNode = null;
//            int minPoint = -1;
//
//            for (int i = 0;i<lists.length;i++) {
//
//                if (lists[i] == null) continue;
//                if (minNode == null ||minNode.val>lists[i].val) {
//
//                    minNode = lists[i];
//                    minPoint = i;
//                }
//            }
//
//            if (minPoint == -1) break;
//
//            tail.next = minNode;
//            tail = tail.next;
//            lists[minPoint] = lists[minPoint].next;
//        }
//        return dummy.next;


    }

}
