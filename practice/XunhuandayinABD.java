package com.project.jvm.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class XunhuandayinABD {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();//控制A
        Condition condition2 = lock.newCondition();//控制B
        Condition condition3 = lock.newCondition();//控制C

        new Thread(() -> {
            try{
                lock.lock();
                for (int i = 0;i<5;i++) {
                    System.out.println("A");
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
                condition3.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try{
                lock.lock();
                for (int i = 0;i<5;i++) {
                    System.out.println("B");
                    condition3.signal();
                    condition2.await();
                }
                condition1.signal();
                condition3.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try{
                lock.lock();
                for (int i = 0;i<5;i++) {
                    System.out.println("C");
                    condition1.signal();
                    condition3.await();
                }
                condition1.signal();
                condition2.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();
    }
}
