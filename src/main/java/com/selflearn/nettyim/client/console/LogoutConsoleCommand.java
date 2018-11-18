package com.selflearn.nettyim.client.console;

import com.selflearn.nettyim.client.NettyClient;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created by coding-dong on 2018/11/16.
 */
public class LogoutConsoleCommand implements ConsoleCommand{
    @Override
    public void execCommand(Scanner scanner, Channel channel) {
        System.out.println("channel[" + channel.id().toString() + "] would be exit" );
        NettyClient.running = false;
    }
}
