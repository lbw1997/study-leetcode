package com.project.jvm.reference;

import java.lang.ref.WeakReference;

/**
 * 如果没有强引用指向弱引用指向的对象，那么该对象就会被垃圾回收
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> t1 = new ThreadLocal<>();
        t1.set(new M());
        t1.remove();
    }
}
