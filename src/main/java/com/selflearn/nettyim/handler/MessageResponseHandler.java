package com.selflearn.nettyim.handler;

import com.alibaba.fastjson.JSONObject;
import com.selflearn.nettyim.protocol.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by coding-dong on 2018/11/7.
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
//        System.out.println("receive message response from server : " + ctx.channel().id().toString());
        System.out.println("receive message [" + msg.getData().get("msg") + "] from userId[" + msg.getData().get("fromUserId") + "]");
        System.out.println(JSONObject.toJSONString(msg));
    }
}
