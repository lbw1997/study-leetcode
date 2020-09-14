package com.project.jvm.thread;

import java.util.Date;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0;i<10;i++) {
            System.out.println(Thread.currentThread().getName() +"执行时间是"+new Date().getTime()+"循环次数是"+i);
        }
    }
}
