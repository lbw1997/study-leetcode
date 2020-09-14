package com.project.jvm.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class NIOtest {

    public static void main(String[] args) {

        try {
            createFiles("nio.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFiles(String fileName) throws IOException {
        ByteBuffer bookBuf = ByteBuffer.wrap("test".getBytes(StandardCharsets.UTF_8));
        ByteBuffer autBuf = ByteBuffer.wrap("java nio".getBytes(StandardCharsets.UTF_8));

        int bookLen = bookBuf.limit();
        int autLen = autBuf.limit();

        ByteBuffer[] byteBuffers = new ByteBuffer[]{bookBuf,autBuf};

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("file未创建，准备创建");
            file.createNewFile();

        }

        FileOutputStream out = new FileOutputStream(file);
        FileChannel fc = out.getChannel();

        fc.write(byteBuffers);
        out.close();

        /*ByteBuffer b1 = ByteBuffer.allocate(bookLen);
        ByteBuffer b2 = ByteBuffer.allocate(autLen);

        ByteBuffer[] byteBuffers1 = new ByteBuffer[]{b1,b2};

        FileInputStream fis = new FileInputStream(file);
        FileChannel fc1 = fis.getChannel();
        fc1.read(byteBuffers1);

        String bookName = new String(byteBuffers1[0].array(),StandardCharsets.UTF_8);
        String autName = new String(byteBuffers1[1].array(),StandardCharsets.UTF_8);

        System.out.println(bookName+"---"+autName);*/

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            new Thread(new ReadThread(file,bookLen)).start();
            new Thread(new ReadThread(file,autLen)).start();
        }
    }
}
class ReadThread implements Runnable {

    ReadThread(File file,int len) {
        this.len = len;
        this.file = file;
    }
    private int len;
    private File file;

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(len);
        try {
            FileInputStream fis = new FileInputStream(file);
            FileChannel fc = fis.getChannel();
            fc.read(buffer);

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new String(buffer.array(),StandardCharsets.UTF_8));
    }
}
