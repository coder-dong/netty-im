package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.protocol.packet.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception {
        System.out.println("member : [" + msg.getQuitMember() + "] has quit group");
    }
}
