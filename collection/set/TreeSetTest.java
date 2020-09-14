package com.project.jvm.collection.set;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest {

    public static void main(String[] args) {
        /*TreeSet<Student> treeSet = new TreeSet<>();
        treeSet.add(new Student("aa"));
        treeSet.add(new Student("bb"));
        treeSet.add(new Student("cc"));
        treeSet.add(new Student("dd"));
        Iterator<Student> iterator = treeSet.iterator();
        iterator.forEachRemaining(s-> System.out.println(s.name));*/


        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("zz");
        treeSet.add("ee");
        treeSet.add("aaa");
        treeSet.add("dd");
        treeSet.add("bb");
        treeSet.add("cc");
        treeSet.add("gg");
        treeSet.add("ff");
        treeSet.add("aa");
        Iterator<String> iterator = treeSet.iterator();
        iterator.forEachRemaining(System.out::println);
    }
}

class Student implements Comparable {

    String name;

    Student(String name) {
        this.name = name;
    }

    //1为正序输出，-1为倒序输出
    @Override
    public int compareTo(Object o) {
        return -1;
    }
}
