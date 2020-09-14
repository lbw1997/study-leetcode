package com.project.jvm.concurrent.chaptor14;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCountTest {

    private ReentrantLock lock = new ReentrantLock();

    public void lock() {
        lock.lock();
    }

    public static void main(String[] args) {
        ReentrantLockCountTest test = new ReentrantLockCountTest();

        for (int i = 0;i<10;i++) {
            test.lock();
        }
        test.lock.unlock();
        System.out.println(test.lock.getHoldCount());
    }
}
