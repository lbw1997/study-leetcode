package com.project.jvm.thread;

import java.util.Date;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        String string = null;
        for (int i = 0;i<10;i++) {
            System.out.println(Thread.currentThread().getName() +"执行时间是"+new Date().getTime()+"循环次数是"+i);
            if (i == 5) {
                string = "MyCallable执行退出";
            }
        }
         return string;
    }
}
