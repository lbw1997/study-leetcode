package com.project.jvm.collection.map;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * LinkedHashMap的accessOrder为true时，在进行每次查询后，都会把查询到的节点移动到链表尾处，
 * 即，保证查询过的在链表尾，而链表头的节点都会所要查询的节点。
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {

        LinkedHashMap<String,Integer> linkedHashMap = new LinkedHashMap<>(16,0.75f,true);

        HashMap<String,Integer> hashMap = new HashMap<>();

        for (int i = 0;i<50000;i++) {
            linkedHashMap.put("user:"+i+" ",i);
           // hashMap.put("user:"+i+" ",i);
        }

        long start = System.nanoTime();

        for (int i = 0;i<500;i++) {
            linkedHashMap.get("user:"+i+" ");
            System.out.println(linkedHashMap);
            /*hashMap.get("user:"+i+" ");
            System.out.println(hashMap);*/
        }

        long end = System.nanoTime();

        System.out.println(end-start);//linked-3.1s hashMap - 2.4
    }
}
