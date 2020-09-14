package com.project.jvm.concurrent.chaptor14;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 * CountDownLatch的release和唤醒线程的操作都是随机性的
 */
public class CountDownLatchTest {

    private static CountDownLatch lock = new CountDownLatch(10);

    public static void main(String[] args) {
        for (int i = 0;i<10;i++) {
            Thread t = new MyTask();
            t.start();

        }
    }

    private static class MyTask extends Thread {
        @Override
        public void run() {
            lock.countDown();
            System.out.println(this.hashCode()+":"+lock.getCount());
            try {
                lock.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(this.hashCode());
            }
        }
    }
}
