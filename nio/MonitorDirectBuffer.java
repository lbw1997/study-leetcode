package com.project.jvm.nio;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicLong;

public class MonitorDirectBuffer {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> aClass = Class.forName("java.nio.Bits");
        Field maxMemory = aClass.getDeclaredField("maxMemory");
        maxMemory.setAccessible(true);
        Field reservedMemory = aClass.getDeclaredField("reservedMemory");
        reservedMemory.setAccessible(true);
        synchronized (aClass) {
            try {
                Long maxMemoryValue = (Long) maxMemory.get(null);
                AtomicLong reservedMemoryValue = (AtomicLong) reservedMemory.get(null);
                System.out.println("maxMemoryValue="+maxMemoryValue);
                System.out.println("reservedMemoryValue="+reservedMemoryValue.get());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
