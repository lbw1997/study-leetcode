package com.project.jvm.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class EchoServer {

    private int port;

    EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {

        int port = 8080;
        if (args!=null&&args.length>0) {
            port = Integer.parseInt(args[0]);
            System.out.println(
                    "Usage:"+EchoServer.class.getSimpleName() +"<"+port+">"
            );
        }
        try {
            new EchoServer(port).run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() throws InterruptedException {
        final MyHandler myHandler = new MyHandler();
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //调用该方法就会创建一个新的通道
                        ChannelPipeline pipeline = ch.pipeline().addLast(serverHandler).addLast(myHandler);
                        System.out.println(pipeline.names());
                    }
                });
        try {
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
@Sharable
class EchoServerHandler extends ChannelInboundHandlerAdapter {

    //将消息记录到控制台
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println(
                "Server received: " + in.toString(CharsetUtil.UTF_8));
        ChannelFuture write = ctx.write(in);    //将接收到的消息写给发送者，而不冲刷出站消息
        write.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("回传数据成功！");
            }
        });
        // 在没有对数据进行操作时，需要对资源进行释放
        //ReferenceCountUtil.release(msg);
    }

    //将未决消息冲刷到远程节点，并且关闭该Channel
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    //若出现异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
@Sharable
class MyHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext channelHandlerContext;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ctx = channelHandlerContext;
//        System.out.println("MyHandler加入到pipeline中！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("My Handler Get Message");
    }
}
