package com.project.jvm.io;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;

public class ByteArrayInputStreamTest {

    private static final int LEN = 5;
    // 对应英文字母“abcddefghijklmnopqrsttuvwxyz”
    private static final byte[] arrayLetters = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void main(String[] args) {

        ByteArrayInputStream basic = new ByteArrayInputStream(arrayLetters);
        for (int i = 0; i < LEN; i++) {
            //判断下个元素是否为空
            if (basic.available() >= 0) {
                System.out.print((char) basic.read());
            }
        }
        System.out.println();

        //判断是否支持标记，总是返回true
        if(!basic.markSupported()) {
            System.out.println("mark not supported");
        }

        //标记将读到的下个元素
        basic.mark(0);

        //跳过n个元素
        basic.skip(10);

        byte[] bytes1 = new byte[LEN];
        //从0数Len个元素复制到byte[]数组中
        basic.read(bytes1,0,LEN);
        System.out.println("str1："+new String(bytes1));


        //回到标记点，标记mark值默认为0
        basic.reset();

        byte[] bytes2 = new byte[LEN];
        basic.read(bytes2,0,LEN);
        System.out.println("str2："+new String(bytes2));
    }
}
