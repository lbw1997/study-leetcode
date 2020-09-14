package com.project.jvm.practice;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class MyServer {

    public static void main(String[] args) {
        int port = 80;
        EventLoopGroup g = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(g)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress("127.0.0.1",port))
                .childHandler(new SimpleChannelInboundHandler(){
                    @Override
                    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                        System.out.println("Received data");
                    }
                });
        try {
            ChannelFuture future = bootstrap.bind().sync();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("bind succeed");
                    }else {
                        System.out.println("bind failure");
                        future.cause().printStackTrace();
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
