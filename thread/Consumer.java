package com.project.jvm.thread;

/**
 * 问题一：数据错位
 * 问题二：数据并没有按照要求，生产一个取走一个
 *
 * 对于Message对象来说，在多线程情况下可能会在被其他线程改变值后再获取 从而导致数据错位
 * 处理：对于生产者Producer和消费者Consumer都会使用Message，所以我们用同步锁加在Message的get和set方法中
 * 此时我们已经解决了数据同步问题，但我们仍然面临数据重复的问题需要解决。
 *
 * 那么我们需要使用线程的唤醒和触发机制来解决数据同步问题
 * 问题始终需要围绕着Message来进行，在Message中设置一个信号，
 * 为true时表示可以进行生产，但不可以进行消费
 * 为false时表示可以消费，但不可以进行生产
 *
 */
class MyThreadTest {
    public static void main(String[] args) {
        Message msg = new Message();
        Producer pro = new Producer(msg);
        Consumer con = new Consumer(msg);
        new Thread(pro).start();
        new Thread(con).start();
    }
}
public class Consumer implements Runnable{

    private Message message;
    public Consumer(Message message) {
        this.message = message;
    }
    @Override
    public void run() {
        for (int i = 0;i<40;i++) {
            message.get();
        }
    }
}

class Producer implements Runnable{
    private Message message;
    Producer(Message message) {
        this.message = message;
    }
    @Override
    public void run() {
        for (int i = 0;i<40;i++) {
            if (i%2 == 0) {
                message.set("abkm", "nb");
            }else {
                message.set("fg", "sb");
            }
        }
    }
}

class Message {
    private boolean flag = true; //true表示可以生产，但不可以消费  false表示不可以生产，但可以消费
    private String title;
    private String content;

    public synchronized void set(String title,String content) {
        if (!this.flag) {   //flag为false时，不可以生产
            try {
                super.wait();       //线程挂起
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.content = content;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.title = title;

        this.flag = false;  //生产完毕 设置为false
        super.notify();     //线程唤醒，继续生产
    }

    public synchronized void get() {
        if (this.flag) {    //为true时，不可以消费
            try {
                super.wait();   //线程挂起
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("title："+this.title+"-content："+this.content);

        this.flag = true;   //消费完成
        super.notify();     //线程唤醒，继续消费
    }
}
