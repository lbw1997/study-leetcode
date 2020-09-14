package com.project.jvm.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("aa",1);
        hashMap.put("bb",2);
        hashMap.put("cc",3);
        hashMap.put("dd",4);
        hashMap.put("ee",5);

        new Thread(()->{

            hashMap.forEach((k,v) ->{
                if (k.equals("cc")) {
                    hashMap.remove("cc");
                }
            });

        }).start();

        new Thread(()->{

            hashMap.forEach((k,v)->{
                System.out.println("key:"+k+"  value:"+v);
            });

        }).start();

    }
}
