package com.project.jvm.memory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class Test1 {

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
     * 通过创建指定类的子类完成动态创建
     */
    public static void main(String[] args) {
        for (;;){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Test1.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor)(obj, method, args1, proxy)->
                    proxy.invokeSuper(obj,args1));
            //System.out.println("hello world");

            enhancer.create();
        }
    }
}
