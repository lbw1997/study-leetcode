package com.project.jvm.concurrent.chaptor10;

/**
 * 两个线程以不同的顺序获得多个相同锁的时候就可能出现死锁，
 * 只有请求锁的顺序相同，才不会出现循环的锁依赖现象，也就不会产生死锁了。
 */
public class LeftRightDeadLock {

    private Object left = new Object();
    private Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                System.out.println("left!");
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                System.out.println("right!");
            }
        }
    }

    public static void main(String[] args) {
        LeftRightDeadLock lock = new LeftRightDeadLock();
        for (;;) {
            new Thread(lock::leftRight).start();
            new Thread(lock::rightLeft).start();
        }
    }
}
