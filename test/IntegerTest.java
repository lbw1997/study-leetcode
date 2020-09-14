package com.project.jvm.test;

import java.util.Objects;

/**
 * Integer内部缓存保存了-128 - 127
 */
public class IntegerTest {

    public static void main(String[] args) {

        Integer a = 32;
        Integer b = 32;
        System.out.println(a == b);

        //缓存上限小于128
        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);//false

        //缓存下限大于-129
        Integer e = -129;
        Integer f = -129;
        System.out.println(e == f);//false
    }
}
