package com.project.jvm.io;

import java.io.*;

public class FileOutputStreamTest {

    private static final String FILE_NAME = "file.txt";

    private static File file = new File(FILE_NAME);

    public static void main(String[] args) throws IOException {

        FileOutputStream out1 = new FileOutputStream(file);

        PrintStream printStream1 = new PrintStream(out1);
        printStream1.println("abcdefghijklmnopqrstuvwxyz");

        printStream1.close();

        FileOutputStream out2 = new FileOutputStream(file, true);
        PrintStream printStream2 = new PrintStream(out2);
        printStream2.println("0123456789");

        ObjectOutputStream objOut = new ObjectOutputStream(out2);
        objOut.writeObject(new User("abkm",23));

        objOut.close();
        printStream2.close();

        read();
    }

    public static void read() throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileDescriptor fd = in.getFD();
        FileInputStream in1 = new FileInputStream(fd);

        char c = (char)in.read();
        System.out.println("c1= "+c);

        in.skip(25);

        byte[] bytes = new byte[10];
        in.read(bytes,0,bytes.length);
        System.out.println("c2= "+ new String(bytes));

        BufferedInputStream bufferedInputStream = new BufferedInputStream(in1);
        char c1 = (char) bufferedInputStream.read();
        System.out.println("c3= "+c1);

        in.close();
        in1.close();
    }
}
class User implements Serializable {
    private String name;
    private int age;

    User(String name,int age) {
        this.name = name;
        this.age = age;
    }
}
