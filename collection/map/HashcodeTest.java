package com.project.jvm.collection.map;

import java.util.Objects;

public class HashcodeTest {

    public static void main(String[] args) {
        //重写hashcode方法之后，创建的两个对象hashcode值相等，如果不重写就不相等。
        System.out.println(new CC("123").hashCode());
        System.out.println(new CC("123").hashCode());
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