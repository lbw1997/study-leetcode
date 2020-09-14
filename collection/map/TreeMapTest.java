package com.project.jvm.collection.map;

import java.util.NavigableMap;
import java.util.TreeMap;

public class TreeMapTest {
    
    public static void main(String[] args) {

        TreeMap<String,Integer> treeMap = new TreeMap<>();

        for (int i = 0;i<50;i++) {
            treeMap.put("user:"+i+" ",i);
        }

        /**
         * 逆序对TreeMap进行遍历
         */
        /*NavigableMap<String, Integer> stringIntegerNavigableMap = treeMap.descendingMap();

        stringIntegerNavigableMap.forEach((k, v) -> {
            System.out.println("key:" + k + " value:" + v);
        });*/

        treeMap.forEach((k, v) -> {
            System.out.println("key:" + k + " value:" + v);
        });



    }
}
