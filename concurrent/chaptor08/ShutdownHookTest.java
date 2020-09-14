package com.project.jvm.concurrent.chaptor08;

/**
 * JVM的关闭钩子
 */
public class ShutdownHookTest {

    public void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                for (;;) {
                    System.out.println("===============");
                }
            }
        });
    }

    public static void main(String[] args) {
        ShutdownHookTest shutdownHookTest = new ShutdownHookTest();
        shutdownHookTest.shutdownHook();

        //使用System.exit(0)正常关闭JVM，关闭钩子会启动
//        System.exit(0);

        //而使用Runtime.getRuntime().halt(0)是强制关闭JVM，关闭钩子不会启动
        Runtime.getRuntime().halt(0);
    }
}
