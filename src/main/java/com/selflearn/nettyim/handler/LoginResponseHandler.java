package com.selflearn.nettyim.handler;

import com.alibaba.fastjson.JSONObject;
import com.selflearn.nettyim.session.Session;
import com.selflearn.nettyim.util.LoginUtil;
import com.selflearn.nettyim.protocol.packet.response.LoginResponsePacket;
import com.selflearn.nettyim.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by coding-dong on 2018/11/7.
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        System.out.println("receive login response from server : " + ctx.channel().id().toString());
        System.out.println(JSONObject.toJSONString(msg));

        if (msg.isSuccess() && msg.getStatusCode() == 200){
            String userId = msg.getData().get("userId");
            Session session = new Session();
            session.setUserId(Long.parseLong(userId));

            SessionUtil.bindSession(session, ctx.channel());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
    }
}
