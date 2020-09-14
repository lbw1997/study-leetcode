package com.project.jvm.io.filter;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferOutputStreamTest {

    // 对应英文字母“abcddefghijklmnopqrsttuvwxyz”
    private static final byte[] ArrayLetters = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void main(String[] args) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("BufferedOutputStream.txt"),16);

        out.write(ArrayLetters,0,16);
        out.write('\n');

        for (int i = 0;i<5;i++) {
            out.write(ArrayLetters,0,i);
        }
        out.flush();

        out.write('\n');
        out.write(ArrayLetters);

        out.close();
    }
}
