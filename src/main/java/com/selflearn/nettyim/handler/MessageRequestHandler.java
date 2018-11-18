package com.selflearn.nettyim.handler;

import com.alibaba.fastjson.JSONObject;
import com.selflearn.nettyim.session.Session;
import com.selflearn.nettyim.util.DateUtil;
import com.selflearn.nettyim.protocol.packet.request.MessageRequestPacket;
import com.selflearn.nettyim.protocol.packet.response.MessageResponsePacket;
import com.selflearn.nettyim.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by coding-dong on 2018/11/6.
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        System.out.println("receive business request from client : " + ctx.channel().id().toString());
        System.out.println(JSONObject.toJSONString(msg));

        if (validUser(msg.getToUserId())){
            Session fromUserSession = SessionUtil.getSession(ctx.channel());

            MessageResponsePacket messageResponsePacket = buildResponsePacket(msg.getToUserId(), fromUserSession);

            Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId());

            toUserChannel.writeAndFlush(messageResponsePacket);

            System.out.println("server has send msg from userId[" + fromUserSession.getUserId() + "] to userId [" + msg.getToUserId() + "]");
        }else {
            System.out.println("userId[" + msg.getToUserId() + "] isn't online");
        }
    }

    private MessageResponsePacket buildResponsePacket(long toUserId, Session fromUserSession) {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setSuccess(true);
        messageResponsePacket.setStatusCode(200);
        messageResponsePacket.setErrorMessage("");

        Map<String, String> data = new HashMap<>(4);
        data.put("fromUserId", fromUserSession.getUserId() + "");
        data.put("msg", "hello, i'm happy you like it, time : " + DateUtil.getNowWithYYYYMMDDHHMMSS());

        messageResponsePacket.setData(data);

        return messageResponsePacket;
    }

    private boolean validUser(long toUserId) {
        Channel toUserChannel = SessionUtil.getChannel(toUserId);
        return toUserChannel != null && SessionUtil.hasLogin(toUserChannel);
    }
}
