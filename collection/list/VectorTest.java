package com.project.jvm.collection.list;

import java.sql.Time;
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class VectorTest {

    public static void main(String[] args) {

        Vector<String> vector = new Vector<>(5,5);
        Collections.addAll(vector,"1","12","123","1234","12345","123456");
//        System.out.println(vector.capacity());    每次扩容都增加设置的扩容值

        new Thread(() -> {
            for (int i = 0;i<vector.size();i++) {
                vector.remove(i);
                vector.add("a");
            }
        }).start();

        new Thread(() -> {
            for (int i = 0;i<vector.size();i++) {
                System.out.println(vector.get(i));
            }
        }).start();

    }
}
class C1 {
    C1(Vector<String> vector) {
        new Thread(() -> {
            vector.removeIf("123"::equals);
        }).start();
    }
}
