package com.project.jvm.test;

/**
 * 模仿CAS自旋操作，使线程空转，但这种方法会消耗CPU资源，只适合用于执行速度快的代码中。
 */
public class UseBooleanToExg {

    private static volatile boolean flag = true;

    private static Object obj = new Object();

    public static void main(String[] args) {

       char[] c1 = "123456".toCharArray();
       char[] c2 = "ABCDEF".toCharArray();

        new Thread(() -> {
            for (char c : c1) {
                while(!flag) {}
                System.out.print(c);
                flag = false;
            }
        }).start();

        new Thread(() -> {
            for (char c : c2) {
                while(flag) {}
                System.out.print(c);
                flag = true;
            }
        }).start();
    }
}
