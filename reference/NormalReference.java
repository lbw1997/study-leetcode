package com.project.jvm.reference;

/**
 * 强引用，一个对象只能调用一次finalize方法。
 */
public class NormalReference {

    public static void main(String[] args) {
        M m = new M();
        m = null;
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
    }
}
class M{

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize----------");
    }
}
