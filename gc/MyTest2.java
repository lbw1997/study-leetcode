package com.project.jvm.gc;

/**
 * PretenureSizeThreshold：设置对象超过多少大小时直接在老年代进行分配
 */
public class MyTest2 {

    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] myAlloc = new byte[5 * size];

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
