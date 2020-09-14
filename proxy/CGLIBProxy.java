package com.project.jvm.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * description: CGLIBProxy <br>
 * date: 2020/9/3 22:06 <br>
 * author: libowen <br>
 * version: 1.0 <br>
 */
public class CGLIBProxy {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MysqlReal.class);

        MysqlReal target = new MysqlReal();
//
        enhancer.setCallback(new MethodInterceptorImpl(target));
//
        MysqlReal demo =  (MysqlReal)enhancer.create();
        System.out.println(demo);
        System.out.println(target);
        demo.getName();

    }

    public static class MysqlReal{

        public String getName() {
            System.out.println("execute something sql");
            saveName();
            return "sql result";
        }

        public void saveName() {
            System.out.println("save my name");
        }
    }


    private static class MethodInterceptorImpl implements MethodInterceptor {

        private MysqlReal  target;
        public MethodInterceptorImpl(MysqlReal  real) {
            this.target = real;
        }

        @Override
        public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("before");
            Object o = methodProxy.invoke(target, args);
//            Object o = methodProxy.invokeSuper(object, args);
            System.out.println("after");
            return o;
        }
    }
}
