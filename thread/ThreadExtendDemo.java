package com.project.jvm.thread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExtendDemo {
    public static void main(String[] args) {
        /*//实现Callable接口
        //创建FutureTask实例 创建Runnable接口
        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());
        //创建Thread实例 执行FutureTask
        Thread thread = new Thread(futureTask,"MyCallable");
        thread.start();
        //在主线程打印信息
        for (int i =0;i<10;i++) {
            System.out.println(Thread.currentThread().getName() +"执行时间是"+new Date().getTime()+"循环次数是"+i);
        }
        //获取并打印MyCallable结果
        try {
            String result = futureTask.get();
            System.out.println("MyCallable执行结果是："+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //使用线程池创建线程
        //1、使用Executors获取线程池对象
        ExecutorService es = Executors.newFixedThreadPool(10);
        //2、通过线程池对象获取线程并执行MyRunnable
        es.execute(new MyRunnable());
        //3、主线程打印信息
        for (int i = 0;i<10;i++) {
            System.out.println(Thread.currentThread().getName() + "执行时间是" + new Date().getTime() + "循环次数是" + i);
        }
    }
}
