package com.project.jvm.collection.map;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public class HashTableTest {

    public static void main(String[] args) {
        Hashtable<String,Integer> hashtable = new Hashtable<>();
        hashtable.put("aa",1);
        hashtable.put("bb",2);
        hashtable.put("cc",3);
        hashtable.put("dd",4);
        hashtable.put("ee",5);

        /**
         * 使用Enumeration来迭代集合则不会出现
         * ConcurrentModificationException异常
         */
       /* new Thread(()->{
            Enumeration<Integer> elements = hashtable.elements();
            while(elements.hasMoreElements()) {
                System.out.println(elements.nextElement());
            }
        }).start();

        new Thread(()->{
            Enumeration<String> keys = hashtable.keys();
            while(keys.hasMoreElements()) {
                if (keys.nextElement().equals("cc")) {
                    hashtable.remove("cc");
                }
            }
        }).start();*/

        /**
         * 使用了forEach循环，同时也使用到了Entry的iterator迭代器，所以会出现
         * ConcurrentModificationException异常
         */
        new Thread(()->{

            hashtable.forEach((k,v) ->{
                if (k.equals("cc")) {
                    hashtable.remove("cc");
                }
            });

        }).start();

        new Thread(()->{

            /*hashtable.forEach((k,v)->{
                System.out.println("key:"+k+"  value:"+v);
            });*/

            /*for (Map.Entry<String, Integer> stringIntegerEntry : hashtable.entrySet()) {
                System.out.println("key:"+stringIntegerEntry.getKey()+"  value:"+stringIntegerEntry.getValue());
            }*/

        }).start();

    }
}

