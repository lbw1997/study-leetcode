package com.project.jvm.concurrent.chaptor14;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    public static void main(String[] args) {

        System.out.println(readWriteLock.isFair());
    }
}
