package com.project.jvm.concurrent.chaptor08;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * 扩展ThreadPoolExecutor并重写钩子方法，使用日志来记录任务的执行情况
 */
public class TimingThreadPool extends ThreadPoolExecutor {
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final Logger logger = Logger.getLogger("TimeThreadPool");
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    public TimingThreadPool(int corePoolSize,
                            int maximumPoolSize,
                            long keepAliveTime,
                            TimeUnit unit,
                            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        logger.info(String.format("Thread %s: start %s",t,r));
        startTime.set(System.nanoTime());
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            logger.info(String.format("Thread %s : end %s, time = %dns",r,t,taskTime));
        } finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try {
            logger.info(String.format("Terminated :avg time = %dns",totalTime.get()/numTasks.get()));
        } finally {
            super.terminated();
        }
    }

    public static void main(String[] args) {
        TimingThreadPool timingThreadPool = new TimingThreadPool(2,
                10,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());

        timingThreadPool.execute(new MyTask());
        //关闭线程池
        timingThreadPool.shutdown();
    }

    static class MyTask implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName());
                //throw new RuntimeException("RuntimeException");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
