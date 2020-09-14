package com.project.jvm.concurrent.chaptor07;

import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class LogService {

    public LogService() throws FileNotFoundException {
        this.queue = new PriorityBlockingQueue<>();
        this.loggerThread = new LoggerThread();
        this.writer = new FileOutputStream(new File("log.txt"));
    }

    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final FileOutputStream writer;
    private boolean isShutdown;
    private int reservations;

    public void start(){
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
            loggerThread.interrupt();
        }
    }
    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException("isShutdown Exception");
            }
            ++ reservations;
        }
        queue.put(msg);
    }
    private class LoggerThread extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (LogService.this) {
                        if (isShutdown && reservations == 0) {
                            break;
                        }
                        String msg = queue.take();
                        System.out.println(msg);
                        synchronized (LogService.this) {
                            -- reservations;
                        }
                        writer.write(msg.getBytes());
                    }
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            LogService logService = new LogService();
            logService.start();
            AtomicInteger atomicInteger = new AtomicInteger();
            for (int i = 0;i<100;i++) {
                try {
                    logService.log(atomicInteger.incrementAndGet()+":"+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

