package com.project.jvm.concurrent.chaptor07;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 赛跑系统
 */
public class CyclicBarrierTest {

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(10,new Run());
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    static class Run implements Runnable {

        @Override
        public void run() {
            System.out.println("Run！");
        }
    }

    static class Ready implements Runnable {

        @Override
        public void run() {
            Student student = new Student(atomicInteger.incrementAndGet() + "stu", atomicInteger.get());
            System.out.println(student+"ready");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private class Student {
            private String name;
            private int id;

            public Student(String name, int id) {
                this.name = name;
                this.id = id;
            }

            @Override
            public String toString() {
                return "Student{" +
                        "name='" + name + '\'' +
                        ", id=" + id +
                        '}';
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0;i<10 ;i++) {
           executorService.execute(new Ready());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
