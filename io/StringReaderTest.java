package com.project.jvm.io;

import java.io.IOException;
import java.io.StringReader;

public class StringReaderTest {

    public static void main(String[] args) {
        String str = "hello world! java is the best!";
        StringReader stringReader = new StringReader(str);

        new Thread(() -> {
            for (int i = 0; i < str.length(); i++) {
                try {
                    System.out.print((char) stringReader.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < str.length(); i++) {
                try {
                    System.out.print((char) stringReader.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
