package com.project.jvm.concurrent.chaptor11;

import java.util.concurrent.Semaphore;

public class BoundedBuffer<E> {

    private final Semaphore availableItems, availableSpace;
    private final E[] items;
    private int putPosition = 0, takePosition = 0;

    public BoundedBuffer(int capacity) {
        availableItems = new Semaphore(0);
        availableSpace = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull() {
        return availableSpace.availablePermits() == 0;
    }

    public void put(E e) throws InterruptedException {
        availableSpace.acquire();
        doInsert(e);
        availableItems.release();
    }

    public E take() throws InterruptedException {
        availableItems.acquire();
        E e = doTake();
        availableSpace.release();
        return e;
    }

    private synchronized void doInsert(E e) {
        int x = putPosition;
        items[x] = e;
        putPosition = (++x == items.length)?0:x;
    }

    private synchronized E doTake() {
        int x = takePosition;
        E e = items[x];
        items[x] = null;
        takePosition = (x++ == items.length)?0:x;
        return e;
    }
}
