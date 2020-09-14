package com.project.jvm.io;

import java.io.StringWriter;

public class StringWriterTest {

    public static void main(String[] args) {
        char[] arrC = {'1','2','3','4','5','6','7','8','9'};
        StringWriter writer = new StringWriter();
            for (char c: arrC) {
                if (c>'5') {
                    writer.write(c);
                }
            }
            //writer.write(arrC);
        System.out.println(writer.getBuffer());
    }
}
