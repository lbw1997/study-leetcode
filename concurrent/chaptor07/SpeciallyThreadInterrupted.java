package com.project.jvm.concurrent.chaptor07;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 在一个专门的线程中完成中断
 */
public class SpeciallyThreadInterrupted {

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(10);

    public static void timeRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {

        class ReThrowableTask implements Runnable {

            private volatile Throwable t;
            @Override
            public void run() {
                try {
                    //执行计算任务
                    r.run();
                } catch (Throwable t) {
                    //得到抛出的异常
                    this.t = t;
                }
            }
            void rethrow() {
                if (t!=null) {
                    //进行重试
//                    throw launderThrowable(t);
                }
            }
        }

        ReThrowableTask task = new ReThrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();//启动新线程

        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();//发出中断请求
            }
        },timeout,unit);
        taskThread.join(unit.toMillis(timeout));//开启一条新线程等待相同时间后，执行中断任务
        task.rethrow();
    }

    public static void main(String[] args) {
        try {
            timeRun(new OutsideThreadInterrupted.MyRunnable(),5,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
