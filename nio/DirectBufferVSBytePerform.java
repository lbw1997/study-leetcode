package com.project.jvm.nio;

import java.nio.ByteBuffer;

/**
 * DirectBuffer直接分配在内存上，使用起来更快
 * 但在频繁创建和销毁的情况下，开销会远大于在堆上
 */
public class DirectBufferVSBytePerform {

    public static void main(String[] args) {
        DirectBufferVSBytePerform d = new DirectBufferVSBytePerform();
        d.DirectBufferPerform();
        d.ByteBufferPerform();
    }

    public void DirectBufferPerform() {
        long start = System.currentTimeMillis();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(500);

        for (int i = 0;i<10000000;i++) {
            for (int j = 0;j<99;j++) {
                byteBuffer.putInt(j);
            }
            byteBuffer.flip();
            for (int j=0;j<99;j++) {
                byteBuffer.getInt(j);
            }
        }
        byteBuffer.clear();
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        start = System.currentTimeMillis();
        for (int i=0;i<200000;i++) {
            ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(10000);
        }
        end = System.currentTimeMillis();
        System.out.println(end-start);

    }

    public void ByteBufferPerform() {
        long start = System.currentTimeMillis();

        ByteBuffer byteBuffer = ByteBuffer.allocate(500);

        for (int i = 0;i<10000000;i++) {
            for (int j = 0;j<99;j++) {
                byteBuffer.putInt(j);
            }
            byteBuffer.flip();
            for (int j=0;j<99;j++) {
                byteBuffer.getInt(j);
            }
        }
        byteBuffer.clear();
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        start = System.currentTimeMillis();
        for (int i=0;i<200000;i++) {
            ByteBuffer byteBuffer1 = ByteBuffer.allocate(10000);
        }
        end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
