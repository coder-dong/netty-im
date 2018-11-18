package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.protocol.packet.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        System.out.println("member : " + msg.getJoinMember() + " has join group");
    }
}
