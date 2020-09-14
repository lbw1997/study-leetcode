package com.project.jvm.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args!=null && args.length>0) {
            port = Integer.parseInt(args[0]);
        }
        AsyncTimeServerHandle timeServer = new AsyncTimeServerHandle(port);
        new Thread(timeServer,"AIO-AsyncTimeServerHandler-001").start();
    }
}
class AsyncTimeServerHandle implements Runnable{

    private int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel channel;

    AsyncTimeServerHandle(int port) {
        this.port = port;
        try {
            channel = AsynchronousServerSocketChannel.open();
            channel.bind(new InetSocketAddress(port));
            System.out.println("the time server is start in port:"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
        channel.accept(this,
                new AcceptCompletionHandler());
    }
}
class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,AsyncTimeServerHandle> {

    @Override
    public void completed(AsynchronousSocketChannel result,AsyncTimeServerHandle attachment) {
        attachment.channel.accept(attachment,this);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        result.read(byteBuffer,byteBuffer,new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandle attachment) {
        attachment.latch.countDown();
    }
}
class ReadCompletionHandler implements CompletionHandler<Integer,ByteBuffer> {

    private AsynchronousSocketChannel channel;

    ReadCompletionHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] bytes = new byte[attachment.remaining()];
        attachment.get(bytes);
        String rep = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("The time server receive order:"+rep);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(rep)?
                new Date(System.currentTimeMillis()).toString():"BAD QUERY";
        doWrite(currentTime);
    }

    private void doWrite(String currentTime) {
        if (currentTime!=null&&currentTime.trim().length()>0) {
            byte[] bytes = currentTime.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            channel.write(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    if (buffer.hasRemaining()) {
                        channel.write(buffer,buffer,this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer buffer) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
