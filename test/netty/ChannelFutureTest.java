package com.project.jvm.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class ChannelFutureTest {

    public static void main(String[] args) {

        Channel channel = null;
        ChannelFuture future = channel.connect(new InetSocketAddress("127.0.0.1", 8080));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    ByteBuf byteBuf = Unpooled.copiedBuffer("hello".getBytes(StandardCharsets.UTF_8));
                    future.channel().writeAndFlush(byteBuf);
                }else {
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        });
    }
}
