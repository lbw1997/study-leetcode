package com.project.jvm.memory;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class Test5 {

    public static void main(String[] args) {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while(iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver:"+driver.getClass()+"classLoader:"+driver.getClass().getClassLoader());
        }
        System.out.println("当前上下文类加载器："+Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器："+ServiceLoader.class.getClassLoader());
    }
}
