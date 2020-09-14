package com.project.jvm.concurrent.chaptor07;

import java.util.concurrent.CountDownLatch;

public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0;i<nThreads;i++) {
            new Thread(()->{
                try {
                    startGate.await();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    endGate.countDown();
                }
            }).start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
