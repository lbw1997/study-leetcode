package com.project.jvm.leetcode;

import java.util.Stack;

/**
 *多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 *
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 */
public class T430 {

    private class Node {
        Node next;
        Node prev;
        int val;
        Node child;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node flatten(Node head) {
        Stack<Node> stack = new Stack<>();
        Node curr = head;
        Node prev = new Node(-1);
        prev.next = head;

        while (curr!=null) {

            if (curr.child != null) {
                if (curr.next!=null) stack.push(curr.next);

                curr.next = curr.child;
                curr.next.prev = curr;
                curr.child = null;
            }else if (curr.next == null&&!stack.isEmpty()) {
                curr.next = stack.pop();
                curr.next.prev = curr;
            }
            curr = curr.next;
            prev = prev.next;
        }
        return head;
    }
}
