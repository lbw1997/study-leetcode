package com.project.jvm.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayOutputStreamTest {

    private static final int LEN = 5;
    // 对应英文字母“abcddefghijklmnopqrsttuvwxyz”
    private static final byte[] arrayLetters = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void main(String[] args) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        //写入字节
        os.write(0x61);
        os.write(0x62);
        os.write(0x63);
        System.out.println(os);

        //写入从3开始的Len个字节
        os.write(arrayLetters,3, LEN);
        System.out.println(os);

        //返回缓冲区数组大小
        int size = os.size();
        System.out.println(size);

         ByteArrayOutputStream os1 = new ByteArrayOutputStream();
        try {
            //把一个字节流中的数据复制到当前字节流中
            os.writeTo(os1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(os1);
    }
}