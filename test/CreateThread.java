package com.project.jvm.test;

import java.util.concurrent.*;

public class CreateThread {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        T1ExtendsThread t1ExtendsThread = new T1ExtendsThread();
        t1ExtendsThread.start();

        new Thread(new T2implementsRunnable()).start();

        FutureTask<String> task = new FutureTask<>(new T3CallableFuture());
        new Thread(task,"t1").start();
        System.out.println(task.get());

        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0;i<4;i++) {
            executorService.execute(new T4ExecuteService());
        }
        executorService.shutdown();
    }
}

class T1ExtendsThread extends Thread {
    @Override
    public void run() {
        System.out.println("T1ExtendsThread线程启动");
    }
}

class T2implementsRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("T2implementsRunnable线程启动");
    }
}

class T3CallableFuture implements Callable<String>{
    @Override
    public String call() throws Exception {
        return "T3CallableFuture线程启动";
    }
}

class T4ExecuteService implements Runnable {

    @Override
    public void run() {
        System.out.println("T4ExecuteService线程启动");
    }
}