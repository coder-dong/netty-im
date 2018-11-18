package com.selflearn.nettyim.server;

import com.selflearn.nettyim.handler.*;
import com.selflearn.nettyim.server.handler.ServerHandler;
import com.selflearn.nettyim.server.handler.ServerPacketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * Created by coding-dong on 2018/10/28.
 */
public class NettyServer {

    public void startServer(int port, String host){
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelHandler() {
                    @Override
                    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

                    }

                    @Override
                    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

                    }
                })
                .option(ChannelOption.SO_BACKLOG, 512)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("fixLengthDecoder", new LengthFieldBasedFrameDecoder(1024 * 10, 0, 2, 0, 2));
//                        ch.pipeline().addLast("messageDecoder", new StringDecoder());

                        ch.pipeline().addLast("fixLengthEncoder", new LengthFieldPrepender(2, 0));
//                        ch.pipeline().addLast("messageEncoder", new StringEncoder());

//                        ch.pipeline().addLast(new ServerHandler());
//                        ch.pipeline().addLast(new ServerPacketHandler());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new CreateGroupRequestHandler());
                        ch.pipeline().addLast(new JoinGroupRequestHandler());
                        ch.pipeline().addLast(new QuitGroupRequestHandler());
                        ch.pipeline().addLast(new ListGroupRequestHandler());
                        ch.pipeline().addLast(new MessageGroupRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());

                    }
                });

        bind(serverBootstrap, 1000);
    }

    private void bind(ServerBootstrap serverBootstrap, int port){
        serverBootstrap.bind(port).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()){
                    System.out.println(new Date().toInstant().getNano() + ": 端口[" + port + "]绑定成功!");
                }else {
                    System.out.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }

    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.startServer(1000, "localhost");
    }
}
