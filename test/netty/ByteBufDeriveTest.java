package com.project.jvm.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

public class ByteBufDeriveTest {

    public void slice() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty in Action rocks!".getBytes(StandardCharsets.UTF_8));
        ByteBuf sliced = byteBuf.slice(0, 15);

        System.out.println(sliced.toString());

        byteBuf.setByte(0,(byte)'j');
        assert byteBuf.getByte(0) == sliced.getByte(0);
    }

    public void copy() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Netty in Action rocks!".getBytes(StandardCharsets.UTF_8));
        ByteBuf copied = byteBuf.copy(0, 15);
        System.out.println(copied.toString());

        byteBuf.setByte(0,(byte)'J');
        assert byteBuf.getByte(0) != copied.getByte(0);

    }

    public static void main(String[] args) {
        ByteBufDeriveTest b = new ByteBufDeriveTest();
        b.slice();
        b.copy();
    }
}
