package com.project.jvm.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量可以用于控制线程并发数量
 * semaphore.acquire()方法用于获取可运行的线程，
 */
public class SemaphoreTest {

    static final int nThreads = 10;
    static final int clientCount = 3;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        Semaphore semaphore = new Semaphore(clientCount);
        for (int i = 0;i<nThreads;i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    System.out.println(semaphore.availablePermits()+" "+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
    }
}
