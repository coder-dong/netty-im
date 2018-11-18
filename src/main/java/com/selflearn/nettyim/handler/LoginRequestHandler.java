package com.selflearn.nettyim.handler;

import com.alibaba.fastjson.JSONObject;
import com.selflearn.nettyim.protocol.packet.request.LoginRequestPacket;
import com.selflearn.nettyim.protocol.packet.response.LoginResponsePacket;
import com.selflearn.nettyim.session.Session;
import com.selflearn.nettyim.util.LoginUtil;
import com.selflearn.nettyim.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by coding-dong on 2018/11/6.
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket>{

    private static AtomicLong counter = new AtomicLong(1);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        System.out.println("receive login request from client : " + ctx.channel().id().toString());
        System.out.println(JSONObject.toJSONString(msg));

        Session session = buildSession(geneUserId(), msg.getUserName());

        Map<String, String> data = new HashMap<>(4);
        data.put("userId", session.getUserId() + "");

        LoginResponsePacket loginResponsePacket = buildResponsePacket(true, 200, "", data);

        SessionUtil.bindSession(session, ctx.channel());

        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private Session buildSession(long userId, String userName) {
        Session session = new Session();

        session.setUserId(userId);
        session.setUserName(userName);

        return session;
    }

    private long geneUserId() {
        return counter.getAndIncrement();
    }

    private LoginResponsePacket buildResponsePacket(boolean success, int statusCode, String errorMsg, Map<String, String> dataMap){
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

        loginResponsePacket.setSuccess(success);
        loginResponsePacket.setStatusCode(statusCode);
        loginResponsePacket.setErrorMessage(errorMsg);
        loginResponsePacket.setData(dataMap);

        return loginResponsePacket;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
    }
}
