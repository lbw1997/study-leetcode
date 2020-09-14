package com.project.jvm.io;

import java.io.*;
import java.util.HashMap;

public class ObjectOutputStreamTest {

    private static final String FILE_NAME = "tmp.txt";

    public static void main(String[] args) throws IOException {
        write();
        try {
            read();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void write() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));

        out.writeBoolean(true);
        out.writeByte((byte)65);
        out.writeChar('a');
        out.writeInt(110);
        out.writeDouble(1.14D);
        out.writeFloat(0.13F);

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("blue","蓝");
        hashMap.put("green","绿");
        hashMap.put("yellow","黄");
        out.writeObject(hashMap);

        out.writeObject(new Stu("haha"));

        out.close();
    }

    public static void read() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(FILE_NAME));
        System.out.println(in.readBoolean());
        System.out.println(in.readByte());
        System.out.println(in.readChar());
        System.out.println(in.readInt());
        System.out.println(in.readDouble());
        System.out.println(in.readFloat());


        HashMap hashMap = (HashMap) in.readObject();
        hashMap.forEach((k,v)->{
            System.out.println("key:"+k+",value:"+v);
        });

        Stu stu = (Stu) in.readObject();
        System.out.println(stu);

        in.close();
    }

}

class Stu implements Serializable {

    private String name;

    Stu(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "name='" + name + '\'' +
                '}';
    }
}
