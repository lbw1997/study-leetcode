package com.project.jvm.concurrent.chaptor14;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 基于AQS实现的二元闭锁
 */
public class OneShotLatch {

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    private final Sync sync = new Sync();

    private class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int arg) {
            //如果闭锁打开则成功 (state == 1)，否则失败
            return (getState() == 1)?1:-1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);    //闭锁已经打开
            return true;    //现在，其他线程可以获得闭锁
        }
    }
}
