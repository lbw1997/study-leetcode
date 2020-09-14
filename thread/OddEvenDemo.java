package com.project.jvm.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 偶数线程打印偶数，奇数线程打印奇数
 *
 * 在考虑多线程问题时，不管哪个线程先执行，只关心由谁来获取某个资源，通过改变线程状态进行判断
 */
public class OddEvenDemo {

    private static int i = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private Object obj = new Object();

    public void odd() {
        while(i<10) {
            lock.lock();
            try{
                if (i%2 == 1) {
                    System.out.println("奇数线程"+i);
                    i++;
                    condition.signal();
                }else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                lock.unlock();
            }
        }
    }

    public void even() {
        while(i<10) {
            lock.lock();
            try {
                if (i%2 == 0) {
                    System.out.println("偶数线程"+i);
                    i++;
                    condition.signal();
                }else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
class OddEvenDemoTest {
    public static void main(String[] args){
        OddEvenDemo o = new OddEvenDemo();
        OddEvenDemoCopy a = new OddEvenDemoCopy();
        new Thread(()->{
            try {
                a.odd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                a.even();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

/**
 * 副本代码用来测试synchronized关键字
 */
class OddEvenDemoCopy {

    private static int i = 0;

    private Object obj = new Object();

    public void odd() throws Exception{
        while(i<10) {
            synchronized (obj) {
                if (i%2 == 1) {
                    System.out.println("奇数线程"+i);
                    i++;
                    obj.notify();
                }else {
                    obj.wait();
                }
            }
        }
    }

    public void even() throws Exception{
        while(i<10) {
            synchronized (obj) {
                if (i%2 == 0) {
                    System.out.println("偶数线程"+i);
                    i++;
                    obj.notify();
                }else {
                    obj.wait();
                }
            }
        }
    }
}