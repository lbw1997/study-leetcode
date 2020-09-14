package com.project.jvm.memory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TLABTest {

    public static int clientTotal = 10000;

    public static int count = 0;

    public static int threadCount = 10000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadCount);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        long start = System.currentTimeMillis();
        for (int i = 0;i<clientTotal;i++) {
            executorService.execute(()->{
                try {
                    Thread.sleep(5000);
                    semaphore.acquire();
                    System.out.println(count++);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("times:"+(end - start));
    }
}
class C {

    private static C objC;
    private C() {}

    public synchronized static C createObject() {
        if (objC == null) {
            objC = new C();
        }
        return objC;
    }
}