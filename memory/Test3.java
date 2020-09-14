package com.project.jvm.memory;

/**
 * 只有在类加载当前类中的静态变量和静态方法时，才算是对类的主动使用
 */
class Parent {
    static int a = 3;
    static {
        System.out.println("Parent static");
    }
    static void doSomething() {
        System.out.println("do something");
    }
}
class Children extends Parent{
    static {
        System.out.println("Children static");
    }
}
public class Test3 {
    public static void main(String[] args) {
        System.out.println(Children.a);
        System.out.println("====================");
        Children.doSomething();
    }

}
