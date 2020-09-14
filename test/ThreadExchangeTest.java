package com.project.jvm.test;

import java.util.concurrent.locks.LockSupport;

public class ThreadExchangeTest {

    public static boolean flag = false;

    private static Thread t1 = null;
    private static Thread t2 = null;

    public static void main(String[] args) {
        char[] c = new char[26];
        for (int i = 0;i<c.length;i++) {
            c[i] = (char) ('A'+i);
        }
        t1 = new Thread(()->{
            for (int i = 0;i<26;i++) {
                System.out.print(c[i]);
                LockSupport.unpark(t2);
                LockSupport.park();

            }
        });

        t2 = new Thread(()->{
            for (int i = 0;i<26;i++) {
                LockSupport.park();
                System.out.print(i+1);
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        t2.start();
    }

}
