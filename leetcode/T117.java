package com.project.jvm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class T117 {

    public Node connect(Node root) {

//        if (root == null) return null;
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(root);
//
//        while (queue.size()>0) {
//            //记录每层的节点个数
//            int size = queue.size();
//            for (int i = 0;i<size;i++) {
//                Node node = queue.poll();
//                //连接Next指针，每层最后一个节点不需要使用Next
//                if (i<size-1) {
//                    node.next = queue.peek();
//                }
//                if (node.left != null) queue.add(node.left);
//                if (node.right != null) queue.add(node.right);
//            }
//        }
//        return root;

        //2、因为每层节点通过Next进行连接，可以看作成链表
        if (root == null) return null;
        Node head = root;
        while (head!=null) {

            Node level = new Node();
            Node tail = level;
            while (head!=null) {
                if (head.left!=null) {
                    tail.next = head.left;
                    tail = tail.next;
                }
                if (head.right!=null) {
                    tail.next = head.right;
                    tail = tail.next;
                }
                head = head.next;
            }

            head = level.next;
        }
        return root;
    }
}
