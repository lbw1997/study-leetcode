package com.project.jvm.concurrent.chaptor10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class DCLInstance {

    private volatile static MyObject obj;

    private DCLInstance(){

    }

    public static MyObject getInstance() {
        if (obj == null) {
            synchronized (DCLInstance.class) {
                if (obj == null) {
                    obj = new MyObject();
                }
            }
        }
        return obj;
    }
    private static class MyObject {
        private AtomicInteger a = new AtomicInteger();
        private AA aa = new AA();
        MyObject() {
        }

        private class AA {
            AA() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class WorkTask implements Runnable {
        @Override
        public void run() {
            try {
                MyObject instance = getInstance();
                if (instance.aa == null) {
                    System.out.println(System.nanoTime());
                }
            } catch (Exception e) {
            }
        }
    }
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (;;) {
            exec.execute(new WorkTask());
        }
    }
}
