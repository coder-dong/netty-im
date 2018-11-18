package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.attribute.Attributes;
import com.selflearn.nettyim.protocol.packet.request.ListGroupRequestPacket;
import com.selflearn.nettyim.protocol.packet.response.ListGroupResponsePacket;
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
public class ListGroupRequestHandler extends SimpleChannelInboundHandler<ListGroupRequestPacket>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupRequestPacket msg) throws Exception {
        long groupId = Long.parseLong(msg.getGroupId());

        DefaultChannelGroup channelGroup = SessionUtil.getGroup(groupId);

        ListGroupResponsePacket listGroupResponsePacket = new ListGroupResponsePacket();

        if (channelGroup != null){
            List<String> memberList = channelGroup.stream()
                    .map(channel -> channel.attr(Attributes.SESSION).get().getUserName()).collect(Collectors.toList());
            listGroupResponsePacket.setMemberList(memberList);
        }else {
            System.out.println("group id isn't exists");
            listGroupResponsePacket.setMemberList(Collections.emptyList());
        }

        ctx.channel().writeAndFlush(listGroupResponsePacket);
    }
}
