package com.project.jvm.practice;

import java.util.Stack;

/**
 * 倒序输出链表元素
 */
public class InvertedOrderLinked {

    public static void print(Node head) {
        if (head == null && head.next == null) return;

        //1、利用栈结构来倒序输出
//        Stack<Node> stack = new Stack<>();
//        Node node = head.next;
//
//        while(node!=null) {
//            stack.push(node);
//            node = node.next;
//        }
//        while(!stack.isEmpty()) {
//            System.out.println(stack.pop().data);
//        }

        //2、利用递归实现倒序输出
//        if (head.next!=null)
//        print(head.next);
//        System.out.println(head.data);

        //3、改变链表数据结构，使其next指针方向变更

        Node cur = head.next;
        Node pre = head;
        Node next = head.next.next;

        while (cur.next!=null) {
            if (cur == head.next) {
                cur.next = null;
            }else {
                cur.next = pre;
            }

            pre = cur;
            cur = next;
            next = next.next;
        }

        cur.next = pre;
        head.next = cur;

        Node node = head.next;
        while(node!=null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    public static Node construct() {
        Node head = new Node();
        head.next = null;
        Node pre = head;
        for (int i = 1;i<10;i++) {
            Node node = new Node();
            node.data = i;
            pre.next = node;
            pre = node;
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = construct();
//        Node node = head.next;
//        while (node!=null) {
//            System.out.println(node.data);
//            node = node.next;
//        }
        print(head);
    }
}
class Node {
    Node next;
    int data;
}
