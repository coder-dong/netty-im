package com.selflearn.nettyim.handler;

import com.alibaba.fastjson.JSONArray;
import com.selflearn.nettyim.protocol.packet.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by coding-dong on 2018/11/15.
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        System.out.println("channel[" + ctx.channel() + "] has join group, group[" + msg.getGroupId() + "]");
        System.out.println("group[" + msg.getGroupId() + "] has members " + JSONArray.toJSONString(msg.getMemberList()));
    }
}
