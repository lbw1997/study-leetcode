package com.project.jvm.concurrent.chaptor14;

public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    //条件谓词 * no-full isEmpty()
    //条件谓词 * no-empty isFull()


    BoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        doPut(v);
        notifyAll();
    }


    public synchronized V take() throws InterruptedException {
        /*
            在循环中守护条件谓词
         */
        while (isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }
}
