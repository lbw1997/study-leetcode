package com.project.jvm.concurrent.chaptor14;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阀门
 */
public class ThreadGate {

    private boolean isOpen;
    private int generation;         //第几代

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++ generation;
        isOpen = true;
        notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while(!isOpen && arrivalGeneration == generation) {
            wait();
        }
    }

    public static void main(String[] args) {

        ThreadGate t = new ThreadGate();
        AtomicInteger a = new AtomicInteger();
        for (int i = 0;i<10;i++) {
            new Thread(()->{
                try {
                    t.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("nums"+a.incrementAndGet());
            }).start();
        }
        t.open();
    }
}
