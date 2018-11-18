package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.attribute.Attributes;
import com.selflearn.nettyim.protocol.packet.request.MessageGroupRequestPacket;
import com.selflearn.nettyim.protocol.packet.response.ListGroupResponsePacket;
import com.selflearn.nettyim.protocol.packet.response.MessageGroupResponsePacket;
import com.selflearn.nettyim.session.Session;
import com.selflearn.nettyim.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class MessageGroupRequestHandler extends SimpleChannelInboundHandler<MessageGroupRequestPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageGroupRequestPacket msg) throws Exception {
        long groupId = Long.parseLong(msg.getGroupId());

        DefaultChannelGroup channelGroup = SessionUtil.getGroup(groupId);

        Session session = SessionUtil.getSession(ctx.channel());

        MessageGroupResponsePacket messageGroupResponsePacket = new MessageGroupResponsePacket();

        if (channelGroup != null){
            messageGroupResponsePacket.setSender(session.getUserName());
            messageGroupResponsePacket.setMessage(msg.getMessage());

            channelGroup.writeAndFlush(messageGroupResponsePacket);
        }else {
            System.out.println("group id isn't exists");
            messageGroupResponsePacket.setMessage("group id isn't exists");
            messageGroupResponsePacket.setSender(session.getUserName());

            ctx.writeAndFlush(messageGroupResponsePacket);
        }
    }
}
