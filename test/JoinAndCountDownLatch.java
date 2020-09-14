package com.project.jvm.test;

import java.util.concurrent.CountDownLatch;

/**
 * 写一个程序，在main线程中启动100个线程，100个线程完成后，主线程打印“完成”，使用join()和countdownlatch都可以完成，请比较异同。
 */
public class JoinAndCountDownLatch {

    public static void main(String[] args) {
       // useJoin();
        useCountDownLatch();
        System.out.println("完成");
    }

    public static void useJoin() {
        for (int i = 0;i < 100; i ++) {
            try {
                Thread.sleep(100);
                new Thread(()->{

                }).join();
                System.out.println("times"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void useCountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0;i<100;i++) {
            System.out.println("times"+i);
            new Thread(()->{
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
