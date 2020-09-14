package com.project.jvm.thread;

/**
 * 守护线程Demo：
 * 守护线程在主线程运行时仍然存活，但当主线程结束守护线程也随之结束
 * 可以使用守护线程完成对服务器存活状态的检测又叫做心跳检测
 */
public class DaemonThread implements Runnable{

    DaemonThread() {
        Thread daemonThread = new Thread(()->{
            for (int i = 0;i<Integer.MAX_VALUE;i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"守护线程正在执行:"+i);
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    @Override
    public void run() {
        for (int i = 0;i<5;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"正在执行:"+i);
        }
    }
}

class DaemonThreadTest {
    public static void main(String[] args) {
        new Thread(new DaemonThread()).start();
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
