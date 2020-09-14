package com.project.jvm.concurrent.chaptor08;

import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * 延迟队列，控制任务
 * 调度线程池来进行处理，
 *
 */
public class DelayQueueTest {

    private static final ExecutorService executorService = Executors.newScheduledThreadPool(10);
    private static final DelayQueue<MyTask> delayQueue = new DelayQueue<>();

    public static void main(String[] args) {

        delayQueue.add(new MyTask(5,"1"));
        delayQueue.add(new MyTask(8,"2"));
        delayQueue.add(new MyTask(10,"3"));
        delayQueue.add(new MyTask(3,"4"));
        try {
            while(delayQueue.iterator().hasNext()) {
                MyTask poll = delayQueue.poll(5, TimeUnit.SECONDS);
                if (poll!=null) {
                    executorService.execute(poll);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
class MyTask extends TimerTask implements Delayed {

    private long l;
    private String name;

    public MyTask(long l,String name) {
        this.l = l;
        this.name = name;
    }

    /**
     *
     * @param unit
     * @return 返回0或-1时才允许被访问否则进行阻塞
     */
    @Override
    public long getDelay(TimeUnit unit) {
        try {
            l--;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"========"+this.name);
    }
}
