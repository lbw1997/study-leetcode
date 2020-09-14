package com.project.jvm.concurrent.chaptor15;

/**
 * 非阻塞计数器
 * {@link SimulatedCAS
 *
 * 使用AtomicInteger效率更高
 */
public class CasCount {

    private SimulatedCAS value;

    public int get() {
        return value.get();
    }

    public int increment() {
        int v;
        do{
            v = value.get();
        }while (v != value.compareAndSwap(v,v+1));
        return v+1;
    }
}
