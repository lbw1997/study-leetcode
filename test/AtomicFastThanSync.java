package com.project.jvm.test;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 写一个程序，证明AtomXXX类比synchronized更高效
 * 在高争用 高耗时的环境下synchronized效率更高
 * 在低争用 低耗时的环境下CAS效率更高
 */
public class AtomicFastThanSync {

    private static Object object = new Object();

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static int i = 0;

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0;i<10;i++) {
            list.add(new Thread());
        }
        long d1 = System.currentTimeMillis();
        list.forEach(Thread::start);
        list.forEach(o->{
            try {
                syncRun();
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long d2 = System.currentTimeMillis();
        System.out.println(d2-d1);
        System.out.println(atomicInteger.get());
    }

    public static void atomicRun() {
        for (int i = 0;i<150000;i++) {
            atomicInteger.incrementAndGet();
        }
    }

    public static void syncRun() {
        for (int i = 0;i<150000;i++) {
            synchronized (object) {
                i++;
            }
        }
    }
}
