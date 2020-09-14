package com.project.jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK的动态代理方式，
 * 1、实现：需要使用接口抽象方法，代理类需要实现InvocationHandler接口，使用Proxy.newProxyInstance()方法来进行动态创建对象
 * 2、缺点：
 *      被代理对象必须实现一个接口，
 *      被代理对象内部方法互相调用不会触发InvocationHandler接口
 *
 */
public class JDKDynamicProxy {

    public static void main(String[] args) {
        MySQLReal real = new MySQLReal();
        MySQLReal2 real2 = new MySQLReal2();

        MySqlProxy proxyHandler = new MySqlProxy(real);
        MySqlProxy proxy2Handler = new MySqlProxy(real2);

        MySQLInterface proxy = (MySQLInterface)Proxy.newProxyInstance(MySqlProxy.class.getClassLoader(),
                new Class[]{MySQLInterface.class},proxyHandler);

        MySQLInterface proxy2 = (MySQLInterface)Proxy.newProxyInstance(MySqlProxy.class.getClassLoader(),
                new Class[]{MySQLInterface.class},proxy2Handler);

        proxy.getName();
        proxy2.getName();
    }

    private static class MySQLReal implements MySQLInterface{

        @Override
        public String getName() {
            System.out.println("executor sql");
            setName();
            return "sql done";
        }

        @Override
        public void setName() {
            System.out.println("save my name1");
        }
    }

    private static class MySQLReal2 implements MySQLInterface{

        @Override
        public String getName() {
            setName();
            System.out.println("executor sql");
            return "sql done";
        }

        @Override
        public void setName() {
            System.out.println("save my name2");
        }
    }

    private static class MySqlProxy implements InvocationHandler{

        private Object target;

        MySqlProxy(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before method executor");
            Object o = method.invoke(target, args);
            System.out.println("after method executor");
            return o;
        }
    }

    private interface MySQLInterface {
        String getName();
        void setName();
    }
}
