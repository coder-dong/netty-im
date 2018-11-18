package com.selflearn.nettyim.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by coding-dong on 2018/10/29.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client [" + ctx.channel().id().toString() + "] start send message");

        ByteBuf buffer = ctx.alloc().ioBuffer(16);

        buffer.writeBytes(("hello server, current time [" + new Date().toString() + "]").getBytes(Charset.defaultCharset()));

        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;

        System.out.println("client receive message : [" + message + "]");
    }
}
