package com.project.jvm.memory;

public class DeadLockTest {
    public static void main(String[] args) {
        new Thread(()->A.method(),"ThreadA").start();
        new Thread(()->B.method(),"ThreadB").start();
    }
}
class A {
    public static synchronized void method() {
        System.out.println("methodB被调用");
        B.method();
    }
}

class B {
    public static synchronized void method() {
        System.out.println("methodB被调用");
        A.method();
    }
}