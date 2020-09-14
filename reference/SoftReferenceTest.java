package com.project.jvm.reference;


import java.lang.ref.SoftReference;

/**
 * 软引用，内存不够时进行回收，常用做缓存
 */
public class SoftReferenceTest {

    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());
        //再分配一个数组，heap装不下，这时候系统会启动GC，先回收一次，如果不够则把软引用干掉。
        byte[] b = new byte[1024 * 1024 * 15];
        System.out.println(m.get());
    }
}
