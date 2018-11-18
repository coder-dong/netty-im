package com.selflearn.nettyim.test.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * Created by coding-dong on 2018/10/30.
 */
public class ByteBufTest {

    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(16,128);
        byte[] srcBytes = "hello world!".getBytes();

        buffer.writeBytes(srcBytes);
        printBuffer("writeBytes(srcBytes)", buffer);

        buffer.readByte();
        printBuffer("readByte()", buffer);

        buffer.markReaderIndex();
        buffer.readBytes(5);
        printBuffer("readBytes(5)", buffer);

        buffer.markWriterIndex();
        buffer.writeInt(2018);
        printBuffer("writeInt(2018)", buffer);

        buffer.writeByte(1);
        printBuffer("writeByte(1)", buffer);

        buffer.resetReaderIndex();
        buffer.resetWriterIndex();
        printBuffer("resetIndex()", buffer);

        buffer.writeInt(2018);
        printBuffer("writeInt(2018)", buffer);
    }

    private static void printBuffer(String action, ByteBuf buffer){
        System.out.println("after <==================" + action + "================>");
        System.out.println("readerIndex : " + buffer.readerIndex());
        System.out.println("writerIndex : " + buffer.writerIndex());
        System.out.println("capacity : " + buffer.capacity());
        System.out.println("maxCapacity : " + buffer.maxCapacity());
        System.out.println("readable size : " + buffer.readableBytes());
        System.out.println("writable size " + buffer.writableBytes());
        System.out.println("readable : " + buffer.isReadable());
        System.out.println("writable : " + buffer.isWritable());
        System.out.println("max writable size  : " + buffer.maxWritableBytes());
        System.out.println();
    }
}
