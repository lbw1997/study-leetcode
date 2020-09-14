package com.project.jvm.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOTimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args!=null && args.length>0) {
            port = Integer.parseInt(args[0]);
        }
        MultipleTimeServer timeServer = new MultipleTimeServer(port);
        new Thread(timeServer,"NIO-MultipleTimeServer-001").start();
    }
}
class MultipleTimeServer implements Runnable {

    private static Selector selector;

    private static ServerSocketChannel socketChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     * @param port
     */
    MultipleTimeServer(int port) {
        try {
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress(port),1024);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("THE TIME SERVER IS START IN PORT: "+port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while(!stop) {
            try {
                //由selector轮询
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while(iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    handleInput(key);
                    if (key!=null) {
                        key.cancel();
                        if (key.channel()!=null) {
                            key.channel().close();
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector!=null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException{
        if (key.isValid()) {
            // 处理新接入的请求消息
            if (key.isAcceptable()) {
                // Accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                try {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);

                    // Add the new connection to the selector
                    //根据SelectionKey的操作位进行判断
                    sc.register(selector,SelectionKey.OP_READ);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }//以上操作完成了TCP的三次握手

            if (key.isReadable()) {
                // Read the data
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = sc.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String body = new String(bytes);

                    /*//[81, 85, 69, 82, 89, 32, 84, 73, 77, 69, 32, 79, 82, 68, 69, 82, 13, 10]
                    byte[] bytes1 = body.getBytes();
                    //[81, 85, 69, 82, 89, 32, 84, 73, 77, 69, 32, 79, 82, 68, 69, 82]
                    byte[] bytes2 = Arrays.copyOf(bytes1, bytes1.length - 2);
                    String curStr = new String(bytes2);*/

                    System.out.println("THE TIME SERVER RECEIVE ORDER:"+body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?
                            new java.util.Date(System.currentTimeMillis()).toString():"BAD ORDER";
                    doWrite(sc,currentTime);
                }else if (read < 0){
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                }else ;// 读到0字节，忽略
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) {
        if (response!=null&&response.trim().length()>0) {
            byte[] bytes = response.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            try {
                channel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
