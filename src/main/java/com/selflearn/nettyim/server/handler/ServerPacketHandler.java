package com.selflearn.nettyim.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.selflearn.nettyim.util.DateUtil;
import com.selflearn.nettyim.protocol.Packet;
import com.selflearn.nettyim.protocol.PacketCodec;
import com.selflearn.nettyim.protocol.packet.request.LoginRequestPacket;
import com.selflearn.nettyim.protocol.packet.request.MessageRequestPacket;
import com.selflearn.nettyim.protocol.packet.response.LoginResponsePacket;
import com.selflearn.nettyim.protocol.packet.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by coding-dong on 2018/11/1.
 */
public class ServerPacketHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf message = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(message);

        Packet responsePacket = null;

        if (packet != null && packet instanceof LoginRequestPacket){
            System.out.println("receive util message from client : " + ctx.channel().id().toString());
            System.out.println(JSONObject.toJSONString(packet));

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setStatusCode(200);
            loginResponsePacket.setErrorMessage("");
            loginResponsePacket.setData(null);

            responsePacket = loginResponsePacket;
        } else if (packet != null && packet instanceof MessageRequestPacket){
            System.out.println("receive business message from client : " + ctx.channel().id().toString());
            System.out.println(JSONObject.toJSONString(packet));

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setSuccess(true);
            messageResponsePacket.setStatusCode(200);
            messageResponsePacket.setErrorMessage("");
            messageResponsePacket.setData(null);

            responsePacket = messageResponsePacket;
        } else {
            System.out.println("receive error packet!");

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setSuccess(false);
            messageResponsePacket.setStatusCode(500);
            messageResponsePacket.setErrorMessage("packet is illegal");

            responsePacket = messageResponsePacket;
        }

        ByteBuf byteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), responsePacket);

        ctx.channel().writeAndFlush(byteBuf);

        System.out.println("server has send message");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel [" + ctx.channel().id().toString() + "] has active");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel [" + ctx.channel().id().toString() + "] has receive message over");
    }
}
