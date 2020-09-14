package com.project.jvm.gc;

public class MyTest5 {

    public Object instance = null;
    private static final int _1M = 1024 * 1024;

    byte[] bytes = new byte[2 * _1M];

    public static void testGC() {
        MyTest5 myTestA = new MyTest5();
        MyTest5 myTestB = new MyTest5();
        myTestA.instance = myTestB;
        myTestB.instance = myTestA;

        myTestA = null;
        myTestB = null;

        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }

}
