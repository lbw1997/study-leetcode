package com.project.jvm.practice;

import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args) {
        Function<Integer,String> function = a -> "== " + a;
        System.out.println(function.apply(10));

        Function<String,Boolean> function1 = c -> c.length()>2;
        System.out.println((function1.apply("1a")));

        System.out.println((function.andThen(function1).apply(1)));
        //andThen类似consumer，是前一个function执行后结果作为参数传新生成的function执行，结果：true

        Function<Integer,Integer> function2 = c -> c*c;
        //compose和andThen正好逻辑相反，传入的参数function先执行后范围结果作为参数传给新生成的function执行
        System.out.println((function.compose(function2).apply(2)));
        //先执行function2，返回结果作为参数再执行function，结果：== 4
        System.out.println((function.compose(function2).andThen(function1).apply(2)));
        //先执行function2，其次执行function，最后执行function1，结果：true
        System.out.println((function2.compose(function2).apply(2)));
        //先执行第二个function2，返回结果作为参数再执行第一个function2，结果：16
        //递归的实现又多了种办法

        Function<String,String> function3 = Function.identity();//static方法
        System.out.println((function3.apply("hello")));
        //identity定义了一个只返回输入参数的function,结果：hello
    }
}
