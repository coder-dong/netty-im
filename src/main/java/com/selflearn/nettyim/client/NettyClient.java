package com.selflearn.nettyim.client;

import com.selflearn.nettyim.client.console.ConsoleCommandManager;
import com.selflearn.nettyim.client.console.LoginConsoleCommand;
import com.selflearn.nettyim.client.handler.ClientPacketHandler;
import com.selflearn.nettyim.handler.*;
import com.selflearn.nettyim.protocol.packet.request.LoginRequestPacket;
import com.selflearn.nettyim.util.LoginUtil;
import com.selflearn.nettyim.protocol.packet.request.MessageRequestPacket;
import com.selflearn.nettyim.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by coding-dong on 2018/10/28.
 */
public class NettyClient {

    public static final int MAX_RETRY = 5;

    public static volatile boolean running = true;

    public void startClient(int port, String host){
        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);

        bootstrap.group(bossGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("fixLengthDecoder", new LengthFieldBasedFrameDecoder(1024 * 10, 0, 2, 0, 2));
//                        ch.pipeline().addLast("messageDecoder", new StringDecoder());

                        ch.pipeline().addLast("fixLengthEncoder", new LengthFieldPrepender(2, 0));
//                        ch.pipeline().addLast("messageEncoder", new StringEncoder());

//                        ch.pipeline().addLast(new ClientHandler());
//                        ch.pipeline().addLast(new ClientPacketHandler());
                        ch.pipeline().addLast(new PacketEncoder());

                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new QuitGroupResponseHandler());
                        ch.pipeline().addLast(new ListGroupResponseHandler());
                        ch.pipeline().addLast(new MessageGroupResponseHandler());
                    }
                });

        connect(bootstrap, port, host, MAX_RETRY);
    }

    private void connect(Bootstrap bootstrap, int port, String host, int maxRetry) {
        bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()){
                    //开始接受输入
                    startInputThread(((ChannelFuture)future).channel(), bootstrap);
                    System.out.println("channel : " + ((ChannelFuture)future).channel().id().toString() + " has connect to server");
                }else if (maxRetry <= 0){
                    System.out.println("已经尝试了[ " + MAX_RETRY + " ]连接，但是还不鞥连接上，放弃连接！");
                }else {
                    System.out.println("第[ " + (((MAX_RETRY - maxRetry) + 1)) + " ] 重新连接！");
                    int delay = 1 << ((MAX_RETRY - maxRetry) + 1);
                    bootstrap.config().group().schedule(() -> connect(bootstrap, port, host, maxRetry - 1), delay, TimeUnit.SECONDS);
                }
            }
        });
    }

    private void startInputThread(Channel channel, Bootstrap bootstrap) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                Scanner scanner = new Scanner(System.in);

                LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
                ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();

                while (!Thread.interrupted() && running){

                    if (!SessionUtil.hasLogin(channel)){
                        loginConsoleCommand.execCommand(scanner, channel);
                    }else {
                        consoleCommandManager.execCommand(scanner, channel);
                    }
                }

                channel.closeFuture().addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()){
                            System.out.println("channel is closed");
                            bootstrap.config().group().shutdownGracefully();
                            System.out.println("client is closed");
                        }else {
                            System.out.println("channel close error");
                        }
                    }
                });
                channel.close();
            }
        }).start();

    }

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.startClient(1024, "localhost");
    }
}
