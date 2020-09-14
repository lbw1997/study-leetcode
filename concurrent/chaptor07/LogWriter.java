package com.project.jvm.concurrent.chaptor07;

import java.io.*;
import java.util.concurrent.*;

/**
 * 日志系统
 * 生产者消费者模型，多生产者单一消费者，创建一条新线程用来输出日志。
 *
 * 问题：如何关闭日志线程，因为日志线程会让JVM无法正常关闭
 *  解决方案：1、直接关闭，这种方案会忽略了阻塞队列中的日志，而线程会因为队列已满，在log处阻塞，无法从阻塞中解脱
 *
 *  {@link LogService
 */
public class LogWriter {

    private final ExecutorService service = Executors.newSingleThreadExecutor();
    private final BlockingQueue<String> queue;
    private final LogThread logThread;
    private final OutputStream outputStream;

    LogWriter() throws FileNotFoundException {
        queue = new PriorityBlockingQueue<>();
        logThread = new LogThread();
        outputStream = new FileOutputStream(new File("log.txt"));
    }

    public void start() {
        logThread.start();
    }

    public void log(String msg) {
        try {
            service.execute(new LogTask(msg));
        } catch (RejectedExecutionException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() throws InterruptedException, IOException {
        try {
            service.shutdown();
            service.awaitTermination(10,TimeUnit.SECONDS);
        } finally {
            outputStream.close();
        }
    }

    class LogTask implements Runnable {

        private String msg;
        LogTask(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            try {
                queue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class LogThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    String take = queue.take();
                    System.out.println(take);
                    outputStream.write(take.getBytes());
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        try {
            LogWriter logWriter = new LogWriter();
            logWriter.logThread.start();
            for (int i = 0;i<100;i++) {
                logWriter.log("msg"+i+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
