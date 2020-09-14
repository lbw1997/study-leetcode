package com.project.jvm.concurrent.chaptor08;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试饱和策略
 * 饱和策略有：
 * AbortPolicy直接抛出异常
 * DiscardPolicy丢弃当前添加入队列的任务
 * Discard-oldestPolicy丢弃队列中最旧的任务，也就是最先加入队列中的任务，
 * 在优先级队列中优先级最高的任务（在优先级队列中不能使用这种饱和策略）
 * CallRunsPolicy把任务返回给线程池的所有者线程，让这个线程执行一次executor
 */
public class RejectPolicyTest {

    private final ExecutorService executor;

    RejectPolicyTest(ExecutorService executor) {
        this.executor = executor;
    }

    public void submit(Runnable command) {
        executor.execute(command);
    }


    public static void main(String[] args) {
        //如果要使用饱和策略，那么就需要控制线程池大小
        ThreadPoolExecutor exec = new ThreadPoolExecutor(0, 10,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        exec.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        exec.setThreadFactory(new MyThreadFactory());
        RejectPolicyTest reject = new RejectPolicyTest(exec);


        //可以通过使用Executors.unconfigurableExecutorService方法来对已经构造出的Executor线程池进行setter修改
        //需要将ExecutorService强转为ThreadPoolExecutor即可
//        Executor executor = Executors.newCachedThreadPool();
//        ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.
//                unconfigurableExecutorService((ExecutorService) executor);

        final ThreadLocalRandom random = ThreadLocalRandom.current();
        for (;;)
            reject.submit(new MyTask(random.nextInt()));
    }
    static class MyTask implements Runnable {
        private int time;
        MyTask(int time) {
            this.time = time;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"sleep："+time);
        }
    }
    //自定义线程工厂
    static class MyThreadFactory implements ThreadFactory {

        AtomicInteger atomicInteger = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"workerThread-"+atomicInteger.incrementAndGet());
        }
    }
}
