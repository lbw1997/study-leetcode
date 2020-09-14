package com.project.jvm.collection.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUWithLinkedHashMap<K,V> extends LinkedHashMap<K,V> {

    static LRUWithLinkedHashMap<String, Integer> lru = new LRUWithLinkedHashMap<>();

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        System.out.println(lru);
        return lru.size() > MAX_ENTRIES;
    }

    private static int MAX_ENTRIES = 3;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            lru.put("user"+i,i);
        }
    }
}

