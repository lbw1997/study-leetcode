package com.project.jvm.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.logging.Logger;

public class TimeClient {

    public void connect(int port,String host) {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ByteBuf byteBuf = Unpooled.copiedBuffer("$_".getBytes());
                        ch.pipeline()
                               /* .addLast(new LineBasedFrameDecoder(1024))
                        .addLast(new StringDecoder())
                        .addLast(new TimeClientHandlerTCP());*/
                               .addLast(new DelimiterBasedFrameDecoder(1024,byteBuf))
                                .addLast(new StringDecoder())
                                .addLast(new TimeClientHandlerLimit());
                    }
                });

        try {
            ChannelFuture f = b.connect(host,port).sync();

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        if (args!=null&&args.length>0) {
            port = Integer.parseInt(args[0]);
        }
        new TimeClient().connect(port,"127.0.0.1");
    }
}
class TimeClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

    private ByteBuf firstMessage;

    public TimeClientHandler() {
        byte[] rep = "QUERY TIME ORDER".getBytes();
        firstMessage = Unpooled.buffer(rep.length);
        firstMessage.writeBytes(rep);
    }

   /* @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8);
        System.out.println("NOW IS:"+body);
    }
*/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downStream :"+cause.getMessage());
        ctx.close();
    }
}
