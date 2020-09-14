package com.project.jvm.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_safeDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket,"窗口1").start();
        new Thread(ticket,"窗口2").start();
        new Thread(ticket,"窗口3").start();
    }
}

class Ticket implements Runnable{

    private static int ticketNum = 100;

    private Lock lock = new ReentrantLock(true);//true 参数是否是公平锁，多个线程都公平用于执行权 false 独占锁 ReentrantLock重入锁

    @Override
    public void run() {
        while(true) {
            lock.lock();
            try {
                if (ticketNum>0) {
                    Thread.sleep(100);
                    String string = Thread.currentThread().getName();
                    System.out.println(string + "完成售卖，剩余票量" + ticketNum--);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();//锁要释放
            }

        }
    }
}