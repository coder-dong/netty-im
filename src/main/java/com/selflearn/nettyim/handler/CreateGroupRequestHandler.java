package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.attribute.Attributes;
import com.selflearn.nettyim.protocol.packet.request.CreateGroupRequestPacket;
import com.selflearn.nettyim.protocol.packet.response.CreateGroupResponsePacket;
import com.selflearn.nettyim.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by coding-dong on 2018/11/14.
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket>{

    private static final AtomicLong counter = new AtomicLong(1);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        System.out.println("receive create group message from channel : " + ctx.channel().id().toString());

        List<String> userIdList = msg.getUserIdList();


        if (userIdList != null && !userIdList.isEmpty()){
            DefaultChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

            userIdList.stream().forEach(userId -> {
                Channel channel = SessionUtil.getChannel(Long.parseLong(userId));

                channelGroup.add(channel);
            });

            //groupId
            long groupId = buildGroupId();

            SessionUtil.bindGroup(groupId, channelGroup);

            CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
            createGroupResponsePacket.setGroupId(groupId);
            createGroupResponsePacket.setMemberList(channelGroup.stream()
                    .map(channel -> channel.attr(Attributes.SESSION).get().getUserName()).collect(Collectors.toList()));

            channelGroup.writeAndFlush(createGroupResponsePacket);

            System.out.println("server has created group , groupId : " + groupId);
        }else {
            System.out.println("userIdList is empty");
        }
    }

    private long buildGroupId() {
        return counter.getAndIncrement();
    }


}
