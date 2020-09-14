package com.project.jvm.thread;

/**
 * DCL即双重锁验证（Double check lock）
 * volatile关键字，1、保证线程可见 2、禁止指令重排序
 */
public class DCLandVolatile {

    private volatile static DCLandVolatile instance;

    private DCLandVolatile() {}

    public static DCLandVolatile getInstance() {

        if (instance == null) {
            synchronized (DCLandVolatile.class) {
                if (instance == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new DCLandVolatile();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i<40; i++ ) {
            new Thread(()->{
                System.out.println(getInstance().hashCode());
            }).start();
        }
        while(Thread.activeCount()>1) {
            Thread.yield();
        }
    }
}
