package com.project.jvm.collection.map;

import java.util.HashMap;

public class ComputeTest {

    public static void main(String[] args) {

        String str = "hello java, i am vary happy! nice to meet you";

        // jdk1.8的写法
        HashMap<Character, Integer> result2 = new HashMap<>(32);
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            result2.compute(curChar, (k, v) -> {
                if (v == null) {
                    v = 1;
                } else {
                    v += 1;
                }
                return v;
            });
        }
        System.out.println(result2);
    }
}
