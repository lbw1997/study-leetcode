package com.project.jvm.collection.list;

import java.util.*;

public class ArrayListSpliteratorTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        Collections.addAll(list,"1","12","123","1234","12345");

        /*Spliterator<String> spliterator = list.spliterator();
        Spliterator<String> firstSpliterator = spliterator.trySplit();
        Spliterator<String> secondSpliterator = spliterator.trySplit();

        System.out.println("spliterator");
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("firstSpliterator");
        while(firstSpliterator.tryAdvance(System.out::println));
        System.out.println("secondSpliterator");
        while(secondSpliterator.tryAdvance(System.out::println));*/

        //使用spliterator解决并发问题

        Spliterator<String> spliterator = list.spliterator();
        Spliterator<String> stringSpliterator1 = spliterator.trySplit();
        Spliterator<String> stringSpliterator2 = spliterator.trySplit();


        new Thread(()->{

            spliterator.forEachRemaining(s->{
                //list.remove("123");    //java.util.ConcurrentModificationException
                System.out.println(s+Thread.currentThread().getName());
            });
        },"t1").start();

        new Thread(()->{

            stringSpliterator1.forEachRemaining(s->{
                System.out.println(s+Thread.currentThread().getName());
            });
        },"t2").start();

        new Thread(()->{
            stringSpliterator2.forEachRemaining(s->{
                System.out.println(s+Thread.currentThread().getName());
            });
        },"t3").start();
    }
}

class User {
    String name;
    User(String name) {
        this.name = name;
    }
}
