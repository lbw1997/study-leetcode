package com.project.jvm.io;

import java.io.*;

public class FileDescriptorTest {

    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream(FileDescriptor.out);
        try {
            out.write('a');
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
