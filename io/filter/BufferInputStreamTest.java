package com.project.jvm.io.filter;

import java.io.*;

public class BufferInputStreamTest {

    private static File file = new File("BufferedInputStreamTest.txt");

    public static void main(String[] args) throws IOException {

        //read();

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

        new Thread(new MyRun(in)).start();
        new Thread(new MyRun(in)).start();
    }
    private static void read() throws IOException {
        byte[] bytes = new byte[1024];

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

        for (int i = 0;i <5;i++) {
            if (in.available()>0) {
                int read = in.read();
                System.out.print((char) read);
            }
        }

        if (!in.markSupported()) {
            System.out.println("mark not supported");
        }

        in.skip(21);

        in.read(bytes);
        System.out.println(new String(bytes));

        in.close();
    }

}
class MyRun implements Runnable {
    private BufferedInputStream in;

    MyRun(BufferedInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            for (;;) {
                 char c = (char) in.read();
                if (c>'z') {
                    break;
                }
                System.out.print(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
