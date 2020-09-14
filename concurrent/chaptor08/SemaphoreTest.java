package com.project.jvm.concurrent.chaptor08;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTest {

    private final Semaphore semaphore = new Semaphore(2);

    private AtomicInteger a = new AtomicInteger(0);

    public void run() {
        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"线程启动");

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }, a.incrementAndGet()+"Thread").start();
    }

    public static void main(String[] args) {
        SemaphoreTest semaphoreTest = new SemaphoreTest();
        for (int i = 0;i<10;i++) {
            semaphoreTest.run();
        }
    }
}
