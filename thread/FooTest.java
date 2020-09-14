package com.project.jvm.thread;

public class FooTest {

    public static void main(String[] args) {
        MyRunnableWithFoo my = new MyRunnableWithFoo();
        Thread printFirst = new Thread(my,"Thread1");
        printFirst.start();
        new Thread(my,"Thread2").start();
        new Thread(my,"Thread3").start();
    }
}

class MyRunnableWithFoo implements Runnable{

    private int num = 3;

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread1")) {
            System.out.println(1);
        }
        if (Thread.currentThread().getName().equals("Thread2")) {
            System.out.println(2);
        }
        if (Thread.currentThread().getName().equals("Thread3")) {
            System.out.println(3);
        }

    }
}
