package com.project.jvm.concurrent.chaptor07;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 使用boolean作为判断中断的标志用来取消任务，
 * 如果生产者的速度超过了消费者，那么队列将会被填满，而put会被阻塞，
 * 此时生产者处于阻塞状态，无法查看cancel标志，从而取消任务，出现并发问题
 *
 * 换做使用interrupt 和 isInterrupt来进行判断，
 * 每个线程都有一个boolean类型的中断状态，中断的时候设置为true
 * 而静态的interrupted方法只能用于清除当前线程的中断状态，并返回它之前的值
 *
 * 并且调用interrupt并不代表必然停止目标线程正在执行的任务；它仅仅是传递了请求中断的信息，
 * 它不会真正中断一个正在运行的线程，它仅仅是发出中断请求，线程会在自己下一个方便的时刻进行中断，这个时刻被称为取消点。
 */
public class BrokenPrimeProducer extends Thread{

    private final BlockingQueue<BigInteger> queue;
//    private volatile boolean cancel = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger big = BigInteger.TEN;
//            while (!cancel) {
            //进行显示检测
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(big);
            }
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
        super.run();
    }

    public void cancel() {
//        cancel = true;
        interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> primes = new PriorityBlockingQueue<>();
        BrokenPrimeProducer primeProducer = new BrokenPrimeProducer(primes);
        primeProducer.start();

        try {
            while(needMorePrimes()) {
                primeProducer.consume();
            }
        } finally {
            primeProducer.cancel();
        }
    }

    private static boolean needMorePrimes() {
        return true;
    }

    private void consume() {
        try {
            BigInteger take = queue.take();
            System.out.println(take);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
