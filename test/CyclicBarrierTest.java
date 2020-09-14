package com.project.jvm.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * public CyclicBarrier(int parties, Runnable barrierAction)
 * Runnable barrierAction即当线程都抵达屏障时要执行的动作，需要实现Runnable接口。
 */
public class CyclicBarrierTest {

    private final static int nThreads = 5;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for (int i = 0;i<nThreads; i++) {
            executorService.execute(new MRun());
        }
        executorService.shutdown();
    }
}

class MRun implements Runnable{

    private static RunBarrier run = new RunBarrier();

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,run);

    @Override
    public void run() {
        System.out.println("Thread:"+Thread.currentThread().getName()+"开始执行");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("Thread:"+Thread.currentThread().getName()+"执行结束");
    }
}
class RunBarrier implements Runnable{

    @Override
    public void run() {
        System.out.println("线程都抵达屏障！结束线程！");
    }
}
