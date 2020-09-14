package com.project.jvm.thread;


import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {

    public static void main(String[] args) throws Exception{
        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());

        Thread thread = new Thread(futureTask,"myThread");
        thread.start();
        for (int i = 0;i<10;i++) {
            System.out.println(Thread.currentThread().getName() + "执行时间是" + new Date().getTime() + "循环次数是" + i);
            for (int j = 0;j<50;j++) {
                futureTask.get();
            }
        }
        try {
            if("MyCallable执行退出".equals(futureTask.get())) {
                Thread.sleep(5000);
            }
            if ("MyCallable接口执行完成".equals(futureTask.get())) {
                futureTask.isDone();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
