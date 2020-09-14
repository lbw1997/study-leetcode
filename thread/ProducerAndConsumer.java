package com.project.jvm.thread;

public class ProducerAndConsumer {
    public static void main(String[] args) {
        MessageTest messageTest = new MessageTest();
        ProducerTest p = new ProducerTest(messageTest);
        ConsumerTest c = new ConsumerTest(messageTest);
        new Thread(p).start();
        new Thread(c).start();
    }
}

class MessageTest {

    private boolean flag = true;

    private String name;
    private String content;
    public synchronized void get() {

        if (flag) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("consumer："+this.name+"---"+this.content);
        flag = true;
        super.notify();

    }
    public synchronized void set(String name, String content) {

        if (!flag) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        this.content = content;
        System.out.println("producer："+this.name+"---"+this.content);


        flag = false;
        super.notify();
    }
}

class ProducerTest implements Runnable{

    private MessageTest messageTest;
    ProducerTest(MessageTest messageTest) {
        this.messageTest = messageTest;
    }
    @Override
    public void run() {
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i%2 == 1) {
                this.messageTest.set("abkm","nb");
            }
            if (i%2 == 0) {
                this.messageTest.set("fg","sb");
            }
        }
    }
}

class ConsumerTest implements Runnable {

    ConsumerTest(MessageTest messageTest) {
        this.messageTest = messageTest;
    }
    private MessageTest messageTest;

    @Override
    public void run() {
        for (int i = 0; i<40; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.messageTest.get();
        }
    }
}