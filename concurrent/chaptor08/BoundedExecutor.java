package com.project.jvm.concurrent.chaptor08;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用ThreadPoolExecutor和Semaphore来控制线程的并发率，在这种情况也就不需要控制线程池的大小
 */
public class BoundedExecutor {

    private final Executor executor;
    private final Semaphore semaphore;


    BoundedExecutor(Executor executor, Semaphore semaphore) {
        this.executor = executor;
        this.semaphore = semaphore;
    }

    public void submitTask(final Runnable command) throws InterruptedException {
        semaphore.acquire();
        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    } finally {
                        semaphore.release();
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        BoundedExecutor bounded = new BoundedExecutor(exec,new Semaphore(5));

        new Thread(()->{
            try {
                for (;;)
                bounded.submitTask(new MyTask());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                for (;;)
                    bounded.submitTask(new MyTask());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static AtomicInteger atomicInteger = new AtomicInteger();

    private static class MyTask implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("MyTask被执行"+atomicInteger.incrementAndGet()+"次");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
