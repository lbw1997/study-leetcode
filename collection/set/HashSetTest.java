package com.project.jvm.collection.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Spliterator;

public class HashSetTest {


    public static void main(String[] args) {
        HashSet<CC> hashSet = new HashSet<>();
        Collections.addAll(hashSet,new CC("1"),new CC("2"),new CC("3"),new CC("4"),new CC("5"));
        hashSet.forEach(s-> System.out.println(s.name));

        Spliterator<CC> spliterator = hashSet.spliterator();
        System.out.println(spliterator.characteristics());

    }
}
class CC {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CC cc = (CC) o;
        return Objects.equals(name, cc.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    CC(String name) {
        this.name = name;
    }
    String name;
}
