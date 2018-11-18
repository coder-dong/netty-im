package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.protocol.packet.request.JoinGroupRequestPacket;
import com.selflearn.nettyim.protocol.packet.response.JoinGroupResponsePacket;
import com.selflearn.nettyim.session.Session;
import com.selflearn.nettyim.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        System.out.println("server receive join group message, joiner[" + ctx.channel().id().toString() + "]");
        long groupId = Long.parseLong(msg.getGroupId());

        SessionUtil.addGroupMember(groupId, ctx.channel());

        Session session = SessionUtil.getSession(ctx.channel());

        DefaultChannelGroup channelGroup = SessionUtil.getGroup(groupId);

        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();

        if (channelGroup != null){
            SessionUtil.addGroupMember(groupId, ctx.channel());

            joinGroupResponsePacket.setJoinMember(session.getUserName());

            channelGroup.writeAndFlush(joinGroupResponsePacket);
        }else {
            joinGroupResponsePacket.setJoinMember("group id isn't exists");
            ctx.writeAndFlush(joinGroupResponsePacket);
        }

    }
}
