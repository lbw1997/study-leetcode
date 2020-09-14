package com.project.jvm.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class NIOComparator {

    public static void main(String[] args) {
        NIOComparator nio = new NIOComparator();
        try {
            //nio.IOMethod();
            nio.NIOMethod();
            //nio.mapMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通IO
     * @throws IOException
     */
    public void IOMethod() throws IOException {
        long start = System.currentTimeMillis();
        DataOutputStream dataOutputStream = new DataOutputStream(
                new FileOutputStream(new File("Comparator.txt")));
        for (int i = 0; i < 4000000; i++) {
            dataOutputStream.writeInt(i);
        }
        dataOutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("IO write time: " + (end - start));


        start = System.currentTimeMillis();
        DataInputStream din = new DataInputStream(
                new FileInputStream(new File("Comparator.txt")));
        for (int i = 0; i < 4000000; i++) {
            din.readInt();
        }
        din.close();
        end = System.currentTimeMillis();
        System.out.println("IO read time: " + (end - start));
    }

    /**
     * 基于Byte的NIO
     * @throws IOException
     */
    public void NIOMethod() throws IOException {
        long start = System.currentTimeMillis();
        FileOutputStream fs = new FileOutputStream(new File("Comparator.txt"));
        FileChannel foutChannel = fs.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(4000000 * 4);
        for (int i = 0; i < 4000000; i++) {
            byteBuffer.put(int2Byte(i));
        }
        //准备写操作
        byteBuffer.flip();
        foutChannel.write(byteBuffer);
        long end = System.currentTimeMillis();
        System.out.println("NIO write time:" + (end - start));

        start = System.currentTimeMillis();
        FileInputStream in = new FileInputStream(new File("Comparator.txt"));
        FileChannel finChannel = in.getChannel();

        ByteBuffer byteBuffer1 = ByteBuffer.allocate(4000000 * 4);
        finChannel.read(byteBuffer1);
        in.close();

        //准备读操作
        byteBuffer1.flip();

        while (byteBuffer1.hasRemaining()) {
            byte2int(byteBuffer1.get(), byteBuffer1.get(), byteBuffer1.get(), byteBuffer1.get());
        }

        end = System.currentTimeMillis();
        System.out.println("NIO read time:" + (end - start));
    }

    /**
     * 基于内存映射区
     * @throws IOException
     */
    public void mapMethod() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel fc = new RandomAccessFile("Comparator.txt", "rw").getChannel();
        IntBuffer intBuffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, (4000000 * 4)).asIntBuffer();
        for (int i = 0; i < 4000000; i++) {
            intBuffer.put(i);
        }
        fc.close();
        long end = System.currentTimeMillis();
        System.out.println("map write time:" + (end - start));

        start = System.currentTimeMillis();
        FileChannel fr = new FileInputStream("Comparator.txt").getChannel();
        IntBuffer intBuffer1 = fr.map(FileChannel.MapMode.READ_ONLY, 0, fr.size()).asIntBuffer();

        while (intBuffer1.hasRemaining()) {
            intBuffer1.get();
        }
        fr.close();
        end = System.currentTimeMillis();
        System.out.println("map read time:" + (end - start));
    }

    private static int byte2int(byte b1, byte b2, byte b3, byte b4) {
        return ((b1 & 0xff) << 24) | ((b2 & 0xff) << 16) | ((b3 & 0xff) << 8) | (b4 & 0xff);
    }

    private byte[] int2Byte(int res) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (res & 0xff);//最低位
        targets[2] = (byte) ((res >> 8) & 0xff);//次低位
        targets[1] = (byte) ((res >> 16) & 0xff);//次高位
        targets[0] = (byte) ((res >>> 24));//最高位，无符号右移
        return targets;
    }
}
