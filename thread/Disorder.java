package com.project.jvm.thread;

public class Disorder {
    private static int a = 0, b = 0;
    private static int x = 0, y = 0;

    public static void main(String[] args) {
        int i = 0;
        for (;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread one = new Thread(()->{
               a = 1;
               x = b;
            });
            Thread other = new Thread(()->{
               b = 1;
               y = a;
            });
            one.start();
            other.start();
            try {
                one.join();
                other.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String result = "第"+i+"次("+x+","+y+")";
            if (x == 0 && y == 0) {
                System.err.println(result);
                //第173754次(0,0)
                break;
            }
        }
    }
}
