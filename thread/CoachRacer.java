package com.project.jvm.thread;

import java.util.concurrent.CountDownLatch;

public class CoachRacer {

    private CountDownLatch countDownLatch = new CountDownLatch(3);

    public void coach() {
        String name = Thread.currentThread().getName();
        System.out.println(name+"等待运动员准备");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name+"运动员到齐，开始训练！");
    }

    public void racer() {
        String name = Thread.currentThread().getName();
        System.out.println(name+"正在准备。。");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name+"准备完毕");
        countDownLatch.countDown();
    }

    public static void main(String[] args) {
        final CoachRacer coachRacer = new CoachRacer();
        new Thread(coachRacer::coach,"教练").start();
        new Thread(coachRacer::racer,"运动员1").start();
        new Thread(coachRacer::racer,"运动员2").start();
        new Thread(coachRacer::racer,"运动员3").start();

    }
}
