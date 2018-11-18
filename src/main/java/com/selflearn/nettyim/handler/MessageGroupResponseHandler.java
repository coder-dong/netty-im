package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.protocol.packet.response.MessageGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class MessageGroupResponseHandler extends SimpleChannelInboundHandler<MessageGroupResponsePacket>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageGroupResponsePacket msg) throws Exception {
        System.out.println("receive group member : [" + msg.getSender() + "] come in message : [" + msg.getMessage() + "]");
    }
}
