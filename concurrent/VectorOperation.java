package com.project.jvm.concurrent;


import java.util.concurrent.CountDownLatch;

public class VectorOperation {

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) {

        VectorOperation vectorOperation = new VectorOperation();
        for (int i = 0 ;i<10 ;i++) {
            vectorOperation.run(i);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("主线程完成");
        }
    }

    public void run(int i) {
        new Thread(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"启动");
            countDownLatch.countDown();
        },"线程"+i).start();
    }
}
