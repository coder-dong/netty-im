package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.protocol.packet.request.QuitGroupRequestPacket;
import com.selflearn.nettyim.protocol.packet.response.QuitGroupResponsePacket;
import com.selflearn.nettyim.session.Session;
import com.selflearn.nettyim.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        long groupId = Long.parseLong(msg.getGroupId());

        Session session = SessionUtil.getSession(ctx.channel());

        DefaultChannelGroup channelGroup = SessionUtil.getGroup(groupId);

        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();

        if (channelGroup != null){
            quitGroupResponsePacket.setQuitMember(session.getUserName());

            channelGroup.writeAndFlush(quitGroupResponsePacket);

            SessionUtil.removeGroupMember(groupId, ctx.channel());

            if (channelGroup.isEmpty()){
                SessionUtil.unBindGroup(groupId);
            }
        }else {
            quitGroupResponsePacket.setQuitMember("group id isn't exists");
            ctx.writeAndFlush(quitGroupResponsePacket);
        }
    }
}
