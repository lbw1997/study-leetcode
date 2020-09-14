package com.project.jvm.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup g = new NioEventLoopGroup();     //基于nio的selector创建一个channel
        Bootstrap b = new Bootstrap();      //创建客户端引导类
        b.group(g)                          //指定EventLoopGroup以处理客户端事件，需要适用于NIO的实现
                .channel(NioSocketChannel.class)        //适用于NIO传输的Channel类型
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer() {     //在创建Channel时，向ChannelPipeline中添加一个ClientHandler实例
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new EchoClientHandler()).addLast(new MyClientOutHandler());
                    }
                });
        ChannelFuture f = b.connect().sync();        //连接到远程结点，阻塞等待直到连接完成
        f.channel().closeFuture().sync();            //阻塞直到连接关闭
        g.shutdownGracefully().sync();                  //关闭线程池并且释放所有资源
    }

    public static void main(String[] args) {
        /*if (args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);*/
        String host = "127.0.0.1";
        int port = 8080;
        try {
            new EchoClient(host, port).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//@Sharable标识一个ChannelHandler可以被多个Channel安全的共享
@Sharable
class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    //若出现异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    //当被通知Channel是活跃的，发送一条消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", StandardCharsets.UTF_8));
    }

    //转储已接收的消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println(
                "Client received:" + msg.toString(StandardCharsets.UTF_8)
        );
    }
}

class MyClientOutHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg,
                      ChannelPromise promise) {
        promise.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture f) {
                if (!f.isSuccess()) {
                    f.cause().printStackTrace();
                    f.channel().close();
                }
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HandlerAdded");
    }
}
