package com.project.jvm.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 预计结果为20W，但实际小于20W
 * 原因，关键字volatile只能保证getstatic指令取出的race值，
 * 但在并发情况下iconst_1和iadd指令可能已经被其他线程使用
 */
public class VolatileTest {

    //atomic原子的
    private static AtomicInteger race = new AtomicInteger(0);

    private static void increase() {
        race.incrementAndGet();
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (int i = 0; i<20 ;i++) {
            threads[i] = new Thread(()->{
                for (int j = 0;j<10000;j++) {
                    increase();
                }
            });
            threads[i].start();
        }

        //等待所有累加线程结束
        while(Thread.activeCount()>1) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
