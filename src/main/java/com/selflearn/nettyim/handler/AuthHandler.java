package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.util.LoginUtil;
import com.selflearn.nettyim.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by coding-dong on 2018/11/8.
 */
public class AuthHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())){
            System.out.println("login status is inactive, please login");
            ctx.channel().close();
        }else {
            System.out.println("login status is active, will remove it");
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (SessionUtil.hasLogin(ctx.channel())){
            System.out.println("已经登录过，删除authHandler");
        }else {
            System.out.println("还未登录，异常packet，close channel");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("channel : " + ctx.channel().id().toString() + " is exception , cause : " + cause.getMessage());
        cause.printStackTrace();
        ctx.channel().close();
    }
}
