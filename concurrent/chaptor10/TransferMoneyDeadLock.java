package com.project.jvm.concurrent.chaptor10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 动态加锁顺序产生的死锁
 * 即便加锁顺序是有序的，但锁的对象受外部影响，
 * 可能会因为外部调用顺序而导致产生锁顺序不同，从而产生死锁。
 *
 * {@link TransferMoney
 */
public class TransferMoneyDeadLock {

    public void transferMoney(Account formAccount, Account toAccount, int amount) {
        synchronized (formAccount) {
            synchronized (toAccount) {
                if (amount<0) {
                    throw new IllegalArgumentException("amount设置不能为负数");
                }
                System.out.println(formAccount+"trans:"+amount+"to:"+toAccount);
            }
        }
    }


    private Lock formLock = new ReentrantLock();
    private Lock toLock = new ReentrantLock();
    /**
     * 使用显示锁来解决动态加锁而产生的锁顺序死锁问题，tryLock进行轮询
     */
    public void resolveTransferMoney(Account formAccount, Account toAccount, int amount) {
        if (formLock.tryLock()) {
            try {
                if (toLock.tryLock()) {
                    try {
                        if (amount<0) {
                            throw new IllegalArgumentException("amount设置不能为负数");
                        }
                        System.out.println(formAccount+"trans:"+amount+"to:"+toAccount);
                    } finally {
                        toLock.unlock();
                    }
                }
            } finally {
                formLock.unlock();
            }
        }
    }

    public static class Account {
        private String name;
        Account(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        TransferMoneyDeadLock deadLock = new TransferMoneyDeadLock();
        Account tony = new Account("tony");
        Account abkm = new Account("abkm");
        for (;;) {
//            new Thread(()-> deadLock.transferMoney(tony,abkm,10)).start();
//            new Thread(()-> deadLock.transferMoney(abkm,tony,20)).start();
            new Thread(()-> deadLock.resolveTransferMoney(abkm,tony,20)).start();
            new Thread(()-> deadLock.resolveTransferMoney(tony,abkm,10)).start();
        }
    }
}
