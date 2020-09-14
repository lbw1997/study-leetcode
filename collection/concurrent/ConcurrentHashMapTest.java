package com.project.jvm.collection.concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap<>();

        for (int i = 0;i<50;i++) {
            concurrentHashMap.put("user:"+i+" ",i);
        }

        new Thread(()->{
            concurrentHashMap.forEach((k,v)->{
                System.out.println("key:"+k+" "+"value:"+v);
            });
        }).start();

        new Thread(()->{
            concurrentHashMap.forEach((k,v)->{
                if (k.equals("user:"+20+" ")) {
                    concurrentHashMap.remove("user:"+20+" ");
                }
                if (k.equals("user:"+21+" ")) {
                    concurrentHashMap.remove("user:"+21+" ");
                }
            });
        }).start();
    }
}
