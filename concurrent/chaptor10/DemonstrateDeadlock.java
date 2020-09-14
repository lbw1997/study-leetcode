package com.project.jvm.concurrent.chaptor10;

import java.util.Random;

/**
 * 死锁出现的可能性很高
 */
public class DemonstrateDeadlock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        final Random random = new Random();
        final TransferMoneyDeadLock.Account[] accounts = new TransferMoneyDeadLock.Account[NUM_ACCOUNTS];

        for (int i = 0;i<NUM_ACCOUNTS;i++) {
            accounts[i] = new TransferMoneyDeadLock.Account("a");
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0;i<NUM_ITERATIONS;i++) {
                    int formAccount = random.nextInt(NUM_ACCOUNTS);
                    int toAccount = random.nextInt(NUM_ACCOUNTS);
                    int amount = random.nextInt(1000);
                    transferMoney(formAccount,toAccount,amount);
                }
            }

            private void transferMoney(int formAccount, int toAccount, int amount) {
                TransferMoneyDeadLock.Account form = accounts[formAccount];
                TransferMoneyDeadLock.Account to = accounts[toAccount];
                synchronized (form) {
                    synchronized (to) {
                        System.out.println(amount);
                    }
                }
            }
        }
        for (int i = 0;i<NUM_THREADS;i++) {
            new TransferThread().start();
        }
    }


}
