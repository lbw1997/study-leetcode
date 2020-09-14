package com.project.jvm.memory;

import org.openjdk.jol.info.ClassLayout;

/*
      OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)       //mark word         01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)       //mark word         00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)       //实例数据           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
     12     4        (loss due to the next object alignment)     //对齐补0 padding
     Instance size: 16 bytes

    -XX:-UseCompressedClassPointers      ------- 关闭压缩指针
    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)       //mark word         01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)       //mark word         00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)       //实例数据           00 1c de 15 (00000000 00011100 11011110 00010101) (366877696)
     12     4        (object header)       //实例数据           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     Instance size: 16 bytes
 */
public class ObjectHeadTest {

    public static void main(String[] args) {
        Object o = new Object();

        String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);
    }
}
