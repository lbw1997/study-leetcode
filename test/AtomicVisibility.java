package com.project.jvm.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AtomXXX类可以保证可见性吗？请写一个程序来证明
 */
public class AtomicVisibility {

    private static AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    public static void m() {
        new Thread(()->{
            System.out.println("m start");
            while(atomicBoolean.get()) {

            }
            System.out.println("m end");
        }).start();
    }

    public static void main(String[] args) {
        m();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        atomicBoolean.set(false);
    }
}
