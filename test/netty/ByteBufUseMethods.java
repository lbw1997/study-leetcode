package com.project.jvm.test.netty;

import io.netty.buffer.*;

public class ByteBufUseMethods {

    public static void main(String[] args) {

        ByteBufUseMethods b = new ByteBufUseMethods();
        b.heapBuffer();
        b.directBuffer();
        b.compositeByteBuffer();
    }

    /**
     * 堆缓存区，也叫做支撑数组（backing array）
     */
    public void heapBuffer() {
        ByteBuf heapBuf = new UnpooledHeapByteBuf(UnpooledByteBufAllocator.DEFAULT,10,100);
        heapBuf.writeBytes("heapBuffer".getBytes());
        if (heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            int length = heapBuf.readableBytes();
            handleArray(array,offset,length);
        }
    }

    public void directBuffer() {
        ByteBuf directBuffer = new UnpooledDirectByteBuf(UnpooledByteBufAllocator.DEFAULT,10,100);
        directBuffer.writeBytes("directBuffer".getBytes());
        if (!directBuffer.hasArray()) {
            int length = directBuffer.readableBytes();
            byte[] array = new byte[length];
            directBuffer.getBytes(directBuffer.readerIndex(),array);
            handleArray(array,0,length);
        }
    }

    /**
     * 复合缓冲区，为多个ByteBuf提供一个聚合视图
     */
    public void compositeByteBuffer() {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        ByteBuf headerBuf = new UnpooledDirectByteBuf(UnpooledByteBufAllocator.DEFAULT,10,100);
        ByteBuf bodyBuf = new UnpooledDirectByteBuf(UnpooledByteBufAllocator.DEFAULT,10,100);
        headerBuf.writeBytes("head11".getBytes());
        bodyBuf.writeBytes("body2".getBytes());
        messageBuf.addComponents(headerBuf,bodyBuf);

        messageBuf.removeComponent(0);
        for(ByteBuf b :messageBuf) {
            System.out.println(b);
        }
    }

    public void handleArray(byte[] b,int offset,int length) {
        String s = new String(b, offset, length);
        System.out.println(s);
    }
}
