package com.project.jvm.memory;

public class MyClassLoaderTest {

    public static void main(String[] args) throws Exception {
        MyClassLoader loader1 = new MyClassLoader("loader1");
        Class<?> clazz = loader1.loadClass("com.myproject.jvm.memory.Simple");
        System.out.println(clazz.getClassLoader());
        Object obj = clazz.newInstance();
    }
}
