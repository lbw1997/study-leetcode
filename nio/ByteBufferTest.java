package com.project.jvm.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * NIO的散射与聚集，散射可以将数据写入一组Buffer中，而聚集则是可以从一组Buffer中读取数据，
 * 可以把一块大的缓存区块拆分成小块的Buffer数组，在已知文件具体结构下，可以创造若干符合文件结构的Buffer，
 * 可以提高创建文件的效率。
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        try {
            createFiles("ByteBuffer.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFiles(String fileName) throws IOException {
        ByteBuffer b1 = ByteBuffer.wrap("abukuma".getBytes(StandardCharsets.UTF_8));
        ByteBuffer b2 = ByteBuffer.wrap("libowen".getBytes(StandardCharsets.UTF_8));

        int b1Len = b1.limit();
        int b2Len = b2.limit();

        ByteBuffer[] buffers = new ByteBuffer[]{b1,b2};

        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream out = new FileOutputStream(file);
        FileChannel channel = out.getChannel();
        channel.write(buffers);
        out.close();

        ByteBuffer buffer1 = ByteBuffer.allocate(b1Len);
        ByteBuffer buffer2 = ByteBuffer.allocate(b2Len);
        ByteBuffer[] buffers1 = new ByteBuffer[]{buffer1,buffer2};

        FileInputStream in = new FileInputStream(file);
        FileChannel channel1 = in.getChannel();
        channel1.read(buffers1);

        System.out.println(new String(buffers1[0].array(), StandardCharsets.UTF_8));
        System.out.println(new String(buffers1[1].array(), StandardCharsets.UTF_8));
    }
}
