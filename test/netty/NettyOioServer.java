package com.project.jvm.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class NettyOioServer {

    public void server(int port) throws InterruptedException {
        final ByteBuf byteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi", StandardCharsets.UTF_8));
        ServerBootstrap b = new ServerBootstrap();
        //EventLoopGroup l = new EpollEventLoopGroup();         //使用Epoll传输方式创建，只适用于linux系统
        // EventLoopGroup l = new NioEventLoopGroup();          //非阻塞
        EventLoopGroup l = new OioEventLoopGroup();             //阻塞
        b.group(l)
                .localAddress(new InetSocketAddress(port))
                //.channel(EpollServerSocketChannel.class)  //使用Epoll完成传输，只适用于linux系统
                //.channel(NioServerSocketChannel.class)    //阻塞
                .channel(OioServerSocketChannel.class)      //非阻塞
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                ctx.writeAndFlush(byteBuf.duplicate())
                                        .addListener(ChannelFutureListener.CLOSE);
                            }
                        });
                    }
                });
        try {
            ChannelFuture future = b.bind().sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            l.shutdownGracefully().sync();
        }
    }
}
