package com.project.jvm.gc;

/*
    -verbose:gc
    -Xmx20m
    -Xms20m
    -Xmn10m
    -XX:+PrintGCDetails
    -XX:SurvivorRatio=8
    -XX:+UseConcMarkSweepGC
    -Dfile.encoding=UTF-8
 */
public class MyTest6 {

    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte[] myAlloc1 = new byte[4 * size];

        System.out.println("1111111111");

        byte[] myAlloc2 = new byte[4 * size];

        System.out.println("2222222222");

        byte[] myAlloc3 = new byte[4 * size];

        System.out.println("3333333333");

        byte[] myAlloc4 = new byte[2 * size];

        System.out.println("hello world");
    }
}

