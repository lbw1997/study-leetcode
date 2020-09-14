package com.project.jvm.memory;

/**
 * 调用ClassLoader类的loadClass方法加载一个类，不会对该类进行初始化
 * 使用反射机制时会主动调用类，所以会对类完成初始化操作.
 * 类的主动创建：
 * 1、创建该类的实例
 * 2、初始化一个类的子类
 * 3、反射机制
 * 4、调用类的静态方法
 * 5、访问类或接口的静态变量或者方法
 * 6、JVM表明是引导类加载器加载的类
 * 7、动态创建
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException{
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = classLoader.loadClass("com.project.jvm.memory.CL");
        System.out.println(clazz);
        System.out.println("========================");
        Class<?> aClass = Class.forName("com.project.jvm.memory.CL");
        System.out.println(aClass);
    }
}
class CL{
    static {
        System.out.println("Class CL");
    }
}


