package com.project.jvm.test;

import org.omg.SendingContext.RunTime;

import java.util.concurrent.atomic.AtomicStampedReference;

public class BoxingAndUnboxingTest {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 4;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);             //false
        System.out.println(e == f);             //false
        System.out.println(c == (a+b));         //true
        System.out.println(c.equals(a+b));      //true
        System.out.println(g == (a+b));         //true
        System.out.println(g.equals(a+b));      //false

        //查看CPU核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}