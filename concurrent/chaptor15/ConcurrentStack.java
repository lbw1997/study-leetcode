package com.project.jvm.concurrent.chaptor15;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack<E> {

    private AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E item) {
        Node<E> newNode = new Node<>(item);
        Node<E> oldNode;
        do {
            oldNode = top.get();
            newNode.next = oldNode;
        }while (!top.compareAndSet(oldNode, newNode));
    }

    public E pop() {
        Node<E> oldNode;
        Node<E> newNode;
        do {
            oldNode = top.get();
            if (oldNode==null) {
                return null;
            }else {
                newNode = oldNode.next;
            }
        }while (!top.compareAndSet(oldNode,newNode));
        return oldNode.item;
    }

    private static class Node<E> {
        private Node<E> next;
        private final E item;

        public Node(E item) {
            this.item = item;
        }
    }
}
