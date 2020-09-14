package com.project.jvm.io.filter;

import java.io.*;

public class DataInputStreamTest {

    private static File file = new File("DataOutput.txt");

    public static void main(String[] args) throws FileNotFoundException {
        try {
            write();
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write() throws IOException {
        DataOutputStream out = new DataOutputStream(
                new FileOutputStream(file));

        out.writeBoolean(true);
        out.writeByte(0x65);
        out.writeChar('a');
        out.writeInt(1520);
        out.writeUTF("abkm");

        out.close();
    }

    public static void read() throws IOException {
        DataInputStream in = new DataInputStream(
                new FileInputStream(file));

        System.out.println(in.readBoolean());
        System.out.println(in.readByte());
        System.out.println(in.readChar());
        System.out.println(in.readInt());
        System.out.println(in.readUTF());

    }
}
