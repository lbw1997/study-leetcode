package com.project.jvm.concurrent.chaptor07;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 在线程外安排中断（不要这样做）
 * 该任务不会被中断
 *
 * 在一个专门的线程中中断任务
 * {@link SpeciallyThreadInterrupted
 *
 */
public class OutsideThreadInterrupted {

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(10);

    public static void timeRun(Runnable r, long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        },timeout,unit);
        r.run();
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.nanoTime());
        }
    }

    public static void main(String[] args) {
        timeRun(new MyRunnable(),5,TimeUnit.SECONDS);
    }
}
