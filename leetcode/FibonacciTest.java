package com.project.jvm.leetcode;

public class FibonacciTest {
    public int fibonacci(int n) {
        if(n == 1) return 0;
        if(n == 2) return 1;
        if(n>2) {
            int num = fibonacci(n-1)+fibonacci(n-2);
            return num;
        }
       return 0;
    }

    public static void main(String[] args) {
        FibonacciTest ft = new FibonacciTest();
        System.out.println(ft.fibonacci(10));
    }
}
