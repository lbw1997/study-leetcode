package com.project.jvm.collection.list;

import java.util.*;

/**
 * 在并发情况下，如果使用迭代器遍历数组，在遍历过程中如果对数组进行变动，那么会引起
 * java.util.ConcurrentModificationException异常
 * 所以ArrayList在多线程情况下是不安全的
 */
public class IteratorTest {

    private static Object o = new Object();

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        Collections.addAll(list,"123","1234","12","1","1234");

        /*new Thread(() -> {
            for (String s : list) {
                System.out.println(s);
            }
        }).start();

        new Thread(() -> {
            for (Iterator<String> iterator = list.iterator();iterator.hasNext();) {
                String next = iterator.next();
                if ("123".equals(next)) {
                    list.add("12345");
                    //iterator.remove();
                }
            }
        }).start();*/


        /*ListIterator<String> stringListIterator = list.listIterator(5);
        while(stringListIterator.hasPrevious()) {
            System.out.println(stringListIterator.previous());
        }*/

        List<String> list1 = new ArrayList<>();
        Collections.addAll(list1,"123","1");
        list.retainAll(list1);
        for (String s : list) {
            System.out.println(s);
        }

    }
} 
