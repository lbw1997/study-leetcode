package com.project.jvm.concurrent.chaptor07;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 实现了Thread.UncaughtExceptionHandler类的uncaughtException方法，用于捕捉线程中未捕捉的异常，并进行打印
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE,"Thread terminated with exception" + t.getName(),e);
    }

    public static void main(String[] args) {
        UEHLogger logger = new UEHLogger();
        Thread t1 = new Thread(()->{
            throw new RuntimeException("RuntimeException!");
        },"t1");
        try {
            t1.start();
        } catch (Throwable e) {
            logger.uncaughtException(t1,e);
        }
    }
}
