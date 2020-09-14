package com.project.jvm.practice;

/**
 * 中国象棋中将帅不能对位，同时只能使用一个变量
 */
public class Generals {

    private static byte LeftMask = (byte) 240;
    private static byte RightMask = (byte) 15;
    private static byte i = 0;

    public static void method1() {
        byte i = 81;
        while (i-->0) {
            if ((i/9%3) == (i%9%3)) {
                continue;
            }
            System.out.println("A:"+(i/9+1)+", B:"+(i%9+1));
        }
    }

    public static void method2() {


        for (setHigh((byte)1);getHigh(i)<=9;setHigh((byte)(getHigh(i)+1))) {
            for (setLow((byte)1);getLow(i)<=9;setLow((byte)(getLow(i)+1))) {
                if (getLow(i)%3 != getHigh(i)%3) {
                    System.out.println(getHigh(i)+" "+getLow(i));
                }
            }
        }
    }

    public static byte getHigh(int i) {
        return (byte) ((i & 0xf0) >> 4);
    }

    public static byte getLow(int i) {
        return (byte) (i&0xf);
    }

    /**
     * 给高位赋值
     * @param n 赋值的数
     */
    public static void setHigh(byte n) {
        i = (byte) ((i&RightMask)|n);
    }

    /**
     * 给低位赋值
     * @param n 赋值的数
     */
    public static void setLow(byte n) {
        i = (byte) ((i&LeftMask)|n<<4);
    }

    public static void main(String[] args) {
//        method1();
        setLow((byte) 16);
        System.out.println(i);
//        method2();
    }
}
