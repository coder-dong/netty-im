package com.selflearn.nettyim.handler;

import com.alibaba.fastjson.JSONArray;
import com.selflearn.nettyim.protocol.packet.response.ListGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class ListGroupResponseHandler extends SimpleChannelInboundHandler<ListGroupResponsePacket>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupResponsePacket msg) throws Exception {
        System.out.println("group members : " + JSONArray.toJSONString(msg.getMemberList()));
    }
}
