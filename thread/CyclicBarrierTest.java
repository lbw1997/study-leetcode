package com.project.jvm.thread;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier可以让多个线程在同时启动
 * CyclicBarrier构造方法参数表示：需要同时启动的线程个数
 * CyclicBarrier.await()方法可以让线程暂停
 */
public class CyclicBarrierTest {

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "线程准备执行");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(name +"线程已经启动"+new Date().getTime());
    }

    public static void main(String[] args) {
        final CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        for (int i = 0;i<3;i++) {
            new Thread(()->{
                cyclicBarrierTest.run();
            },"线程"+(i+1)).start();
        }
    }
}
