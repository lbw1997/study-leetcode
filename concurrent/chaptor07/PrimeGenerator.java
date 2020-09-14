package com.project.jvm.concurrent.chaptor07;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 取消策略：详细说明关于取消的“how”，“when”，“what”
 * 也就是要说明，如何取消任务，什么时候取消任务，以及取消任务之后还需要进行什么操作。
 */
public class PrimeGenerator implements Runnable{

    private List<BigInteger> list = new ArrayList<>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger bigInteger = BigInteger.ONE;
        while (!cancelled) {
            bigInteger = bigInteger.nextProbablePrime();
            synchronized (this) {
                list.add(bigInteger);
            }
        }
    }

    public void cancel() {
        this.cancelled = true;
    }
    public synchronized List<BigInteger> getList() {
        return list;
    }

    public static void main(String[] args) {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();

        try {
            SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
          generator.cancel();
        }
        System.out.println(generator.getList());
    }
}


