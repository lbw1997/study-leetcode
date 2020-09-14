package com.project.jvm.gc;

/**
 * PSYoungGen：Parallel Scavenge（新生代垃圾收集器）（多线程 复制算法）
 * ParOldGen: Parallel Old（老年代垃圾收集器） （多线程 标记-整理算法）
 *
 * JDK8的默认
 */
public class MyTest1 {
    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte[] myAlloc1 = new byte[2 * size];
        byte[] myAlloc2 = new byte[4 * size];
        byte[] myAlloc3 = new byte[3 * size];
        //byte[] myAlloc4 = new byte[2 * size];

        System.out.println("hello world");

    }
}
