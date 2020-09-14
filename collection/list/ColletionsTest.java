package com.project.jvm.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ColletionsTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"1","12","123","1234","12345");
        List<String> syncList = Collections.synchronizedList(list);



        new Thread(() -> {
            for (int i = 0;i<syncList.size();i++) {
                syncList.removeIf("1234"::equals);
                System.out.println(syncList);
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (String s : syncList) {
                System.out.println(s);
            }
        }).start();
    }
}
