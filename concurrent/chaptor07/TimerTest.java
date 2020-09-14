package com.project.jvm.concurrent.chaptor07;


import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TimerTest {

    public static void main(String[] args) throws InterruptedException {
        Timer timer1 = new Timer();
        timer1.schedule(new Task1(),1);
        SECONDS.sleep(1);
        timer1.schedule(new Task1(),1);
        SECONDS.sleep(5);
    }
    static class Task1 extends TimerTask {

        @Override
        public void run() {
            System.out.println("111111111111111111");
        }
    }

}
