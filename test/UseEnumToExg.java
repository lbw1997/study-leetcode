package com.project.jvm.test;

/**
 * 使用枚举类完成线程信息传递
 */
public class UseEnumToExg {

    enum ReadyToRun{T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1;   //为什么要使用volatile

    public static void main(String[] args) {

        char[] c1 = "123456789".toCharArray();
        char[] c2 = "ABCDEFGHI".toCharArray();

        new Thread(()->{
            for(char c : c1) {
                while(r!=ReadyToRun.T1) {}
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        }).start();

        new Thread(()->{
            for(char c : c2) {
                while(r!=ReadyToRun.T2) {}
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        }).start();
    }
}


