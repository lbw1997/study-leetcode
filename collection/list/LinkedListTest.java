package com.project.jvm.collection.list;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTest {

    public static void main(String[] args) {

        LinkedList<String> linkedList = new LinkedList<>();
        Collections.addAll(linkedList,"1","12","123","1234", "12345");

        //1、可以从后往前遍历
//        Iterator iterator = linkedList.descendingIterator();
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

        //2、线程不安全问题
        //ConcurrentModificationException
        new Thread(() ->{
            Iterator iterator = linkedList.descendingIterator();
            while(iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }).start();
        new Thread(() ->{
            for (int i = 0;i<linkedList.size();i++) {
                linkedList.remove();
                linkedList.add("a");
            }
        }).start();

        //LinkedList集合克隆
//        LinkedList clone = (LinkedList) linkedList.clone();
//        System.out.println(clone);


    }
}
