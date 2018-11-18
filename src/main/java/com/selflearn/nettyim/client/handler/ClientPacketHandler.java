package com.selflearn.nettyim.client.handler;

import com.selflearn.nettyim.util.LoginUtil;
import com.selflearn.nettyim.protocol.packet.request.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

/**
 * Created by coding-dong on 2018/11/1.
 */
public class ClientPacketHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel [" + ctx.channel().id().toString() + "] is active");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("hello");
        loginRequestPacket.setPassword("password");

//        ByteBuf message = PacketCodec.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

//        ctx.channel().writeAndFlush(message);

        ctx.channel().writeAndFlush(loginRequestPacket);

        System.out.println("client [" + ctx.channel().id().toString() + "] has send message");
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf message = (ByteBuf) msg;
//
//        Packet resPacket = PacketCodec.INSTANCE.decode(message);
//
//        if (resPacket != null && resPacket instanceof LoginResponsePacket){
//            System.out.println("receive util message from server:");
//            System.out.println(JSONObject.toJSONString(resPacket));
//
//            if (((LoginResponsePacket) resPacket).isSuccess() && ((LoginResponsePacket) resPacket).getStatusCode() == 200){
//                LoginUtil.markAsLogin(ctx.channel());
//            }
//        } else if (resPacket != null && resPacket instanceof MessageResponsePacket){
//            System.out.println("receive business message from server:");
//            System.out.println(JSONObject.toJSONString(resPacket));
//        } else {
//            System.out.println("receive error packet");
//        }
//
//    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("channel [" + ctx.channel().id().toString() + "] has finish read");
//    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel has inactive , has close");
        LoginUtil.markAsLogout(ctx.channel());
    }
}
