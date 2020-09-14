package com.project.jvm.thread;

import java.util.concurrent.TimeUnit;

/**
 * 实际上，在Thread类内部维护了一个ThreadLocalMap对象的指针，
 * key表示ThreadLocal对象，value为set进去的值，所以ThreadLocal是每个线程独有的对象
 * ThreadLocalMap中的entry就是继承于WeekReference，entry中所指向ThreadLocal的key就是弱引用，
 * 因为当ThreadLocal进行垃圾回收时，如果key为强引用，那么就不可进行回收会出现内存泄露问题，
 * 但当ThreadLocal被回收后，Entry对象也不会被删除，所以需要手动进行remove操作
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        ThreadLocal<Person> t1 = new ThreadLocal<>();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t1.get());
        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.set(new Person());
        }).start();
    }

    static class Person {
        private String name = "abkm";
    }
}
