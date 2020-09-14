package com.project.jvm.gc;

/*
    -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+SurvivorRatio=8
    -XX:MaxTenuringThreshold=5 -XX:+PrintTenuringDistribution

    -XX:MaxTenuringThreshold=5作用：在可以自动调节对象晋升（Promote）到老年代阈值的GC的中，设置该阈值的最大值。
    该参数的默认值为15，CMS中默认值是6，G1中默认值为15（在JVM中，该数值是由四个bit来表示的，所以最大值就是1111即15）

    经历了多次GC后，存活的对象会在From Survivor与To Survivor之间来回存放，而这里面的一个前提是这两个空间需要有足够的空间来存放这些数据，
    在GC算法中，会计算每个对象的年龄大小，如果达到某个年龄后发现总大小已经大于了Survivor空间的50%，那么这时就需要调整阈值，不能等到
    默认的15次GC后才完成晋升，因为这样会导致Survivor空间不足，所以需要调整阈值，让存活对象尽快完成晋升。

    -XX:+PrintTenuringDistribution作用：
 */
public class MyTest3 {

    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte[] myAlloc1 = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[2 * size];
        byte[] myAlloc4 = new byte[2 * size];

        System.out.println("hello world");
    }
}
