package com.project.jvm.concurrent.chaptor10;

import com.project.jvm.concurrent.chaptor10.TransferMoneyDeadLock.Account;

/**
 * 使用System.identityHashCode方法获取Account对象的hashcode值，
 * 通过比较hashcode值来控制加锁顺序
 * 使用这种情况有可能会出现hash碰撞，那么需要再创建一把加时锁针对这个问题进行处理
 * 当遇到hash碰撞时，先加tieLock锁，就可以使该方法同时只有一个线程调用
 */
public class TransferMoney {

    private final static Object tieLock = new Object();

    public void transferMoney(Account formAccount, Account toAccount, int amount) {
        int form = System.identityHashCode(formAccount);
        int to = System.identityHashCode(toAccount);

        if (form>to) {
            synchronized (formAccount) {
                synchronized (toAccount) {
                    print(amount);
                }
            }
        }else if (form<to){
            synchronized (toAccount) {
                synchronized (formAccount) {
                    print(amount);
                }
            }
        }else {
            synchronized (tieLock) {
                synchronized (formAccount) {
                    synchronized (toAccount) {
                        print(amount);
                    }
                }
            }
        }
    }

    public void print(int amount) {
        if (amount<0) {
            throw new IllegalArgumentException("amount不能为负数");
        }
        System.out.println(amount);
    }

    public static void main(String[] args) {
        TransferMoney deadLock = new TransferMoney();
        Account tony = new Account("tony");
        Account abkm = new Account("abkm");
        for (; ; ) {
            //可能出现的三种情况，需要针对者三种特殊情况进行处理
            new Thread(() -> deadLock.transferMoney(tony, abkm, 10)).start();
            new Thread(() -> deadLock.transferMoney(abkm, tony, 20)).start();
            new Thread(() -> deadLock.transferMoney(abkm, abkm, 20)).start();
        }
    }
}
