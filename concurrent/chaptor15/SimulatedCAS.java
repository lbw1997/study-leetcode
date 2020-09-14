package com.project.jvm.concurrent.chaptor15;

/**
 * 模拟CAS操作
 */
public class SimulatedCAS {

    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = expectedValue;
        if (oldValue == newValue) {
            value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return (expectedValue == compareAndSwap(expectedValue,newValue));
    }
}
