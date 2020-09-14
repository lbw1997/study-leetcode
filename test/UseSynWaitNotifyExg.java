package com.project.jvm.test;

import java.util.concurrent.CountDownLatch;

/**
 * 用synchronized和wait、notify完成线程交叉输出
 * notify用于唤醒线程，使线程可以等待锁的释放
 * wait用于释放锁，并让当前线程进入等待队列
 * 为了解决线程先后执行问题，使用CountDownLatch完成
 */
public class UseSynWaitNotifyExg {

    static char[] c1;
    static char[] c2;

    private final static UseSynWaitNotifyExg u = new UseSynWaitNotifyExg();

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        c1 = "1234567".toCharArray();
        c2 = "ABCDEFG".toCharArray();

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (u) {
                for (char c : c1) {
                    System.out.print(c);
                    try {
                        u.notify();//唤醒线程但唤醒的线程没拿到锁还是不能执行
                        u.wait(); //让出锁进入等待队列
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                u.notify();//必须，否则无法停止程序
            }
        }, "t1").start();


        new Thread(() -> {
            synchronized (u) {
                for (char c : c2) {
                    System.out.print(c);
                    latch.countDown();
                    try {
                        u.notify();
                        u.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                u.notify();
            }
        }, "t2").start();
    }
}
