package com.project.jvm.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 控制CPU使用率到50%
 */
public class CPUController {

    private static long SLEEP_TIME = 50;
    private static long IDLE_TIME = SLEEP_TIME;

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cores = runtime.availableProcessors();

        ExecutorService exec = Executors.newFixedThreadPool(cores);
        for (int i = 0;i<cores;i++) {
            exec.execute(new MyTask());
        }

    }

    public static class MyTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                long startTime = System.currentTimeMillis();
                while(System.currentTimeMillis()-startTime<=IDLE_TIME) {

                }
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
