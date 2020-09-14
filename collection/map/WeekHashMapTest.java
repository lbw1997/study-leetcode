package com.project.jvm.collection.map;

import java.util.WeakHashMap;

public class WeekHashMapTest {

    public static void main(String[] args) {
        WeakHashMap<String,Integer> weakHashMap = new WeakHashMap<>();

        for (int i = 0;i<500;i++) {
            weakHashMap.put("user:"+i+" ",i);
        }

        for (int i = 0;i<30;i++) {
            System.out.println(weakHashMap);
        }

        System.gc();
        System.out.println("gc WeekHashMap==================");
        System.out.println(weakHashMap);

        weakHashMap.clear();
        System.out.println("clear WeekHashMap==================");
        System.out.println(weakHashMap);
    }
}
