package com.project.jvm.gc;

/*
    -verbose:gc
    -Xmx200m
    -Xmn50m
    -XX:TargetSurvivorRatio=60
    -XX:+PrintTenuringDistribution
    -XX:+PrintGCDetails
    -XX:+PrintGCDateStamps
    -XX:+UseConcMarkSweepGC
    -XX:+UseParNewGC
    -XX:MaxTenuringThreshold=3

 */
public class MyTest4 {

    public static void main(String[] args) throws InterruptedException {
        byte[] byte_1 = new byte[512 * 1024];
        byte[] byte_2 = new byte[512 * 1024];

        myGC();
        Thread.sleep(1000);

        System.out.println("111111111111111111");

        myGC();
        Thread.sleep(1000);

        System.out.println("222222222222222222");

        myGC();
        Thread.sleep(1000);

        System.out.println("333333333333333333");

        myGC();
        Thread.sleep(1000);

        System.out.println("444444444444444444");

        byte[] byte_3 = new byte[1024 * 1024];
        byte[] byte_4 = new byte[1024 * 1024];
        byte[] byte_5 = new byte[1024 * 1024];

        myGC();
        Thread.sleep(1000);

        System.out.println("55555555555555555");

        myGC();
        Thread.sleep(1000);

        System.out.println("66666666666666666");

        System.out.println("hello world");
    }

    private static void myGC() {
        for (int i = 0; i < 40; i++) {
            byte[] byteArray = new byte[1024 * 1024];
        }
    }
}
