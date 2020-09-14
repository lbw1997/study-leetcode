package com.project.jvm.practice;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * java数组是类型兼容的，又叫做协变数组类型（covariant array type），现进行测试
 * 因为数组是协变的，那么集合应该也是协变的，现进行测试
 *
 * */
public class CovariantArrayTypeTest {

    public void testArray() {
        //存储的对象类型错误，这将产生ArrayStoreException异常
        Object[] a = new Integer[10];
        a[0] = '1';

        //由于Object是所有对象的父类，并且java数组支持协变类型转换，可以编译
        Object[] b = new Object[10];
        b[0] = '1';
    }

    public void testCollection() {
        //集合的泛型测试

    }

    //把参数列表从    Collection<Shape> arr 改写为 Collection<? extends Shape> arr 即可编译
    public double totalArea(Collection<? extends Shape> arr) {
        double total = 0;
        for (Shape s:arr) {
            if (s!=null) {
                total+= s.area;
            }
        }
        return total;
    }

    public static void main(String[] args) {

        CovariantArrayTypeTest t = new CovariantArrayTypeTest();
        ArrayList<Square> arr = new ArrayList();
        arr.add(new Square(10));
//        t.totalArea(arr);       //编译错误，泛型是不具有协变性质的
        t.totalArea(arr);
    }
}
class Shape {
    public double area;
    public Shape(double area) {
        this.area = area;
    }
}
class Square extends Shape{
    public Square(double area) {
        super(area);
    }
}
