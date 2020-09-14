package com.project.jvm.practice;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ShareEventLoop {

    public static void main(String[] args) {

        ServerBootstrap b = new ServerBootstrap();

        b.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChannelInboundHandler<Channel>() {
                    ChannelFuture future;
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("channel active");
                        Bootstrap b = new Bootstrap();
                        b.channel(NioSocketChannel.class)
                                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                                        System.out.println("Received data");
                                    }
                                });
                        b.group(ctx.channel().eventLoop());
                        future = b.connect(new InetSocketAddress("127.0.0.1", 80));
                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, Channel msg) throws Exception {
                        if (future.isDone()) {
                            System.out.println("连接成功");
                        }
                    }
                });
        ChannelFuture future = b.bind(new InetSocketAddress(8080));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("bind succeed");
                }else {
                    future.cause().printStackTrace();
                    future.channel().close();
                }
            }
        });
    }
}
