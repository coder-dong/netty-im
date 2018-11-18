package com.selflearn.nettyim.client.console;

import com.selflearn.nettyim.protocol.packet.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by coding-dong on 2018/11/16.
 */
public class LoginConsoleCommand implements ConsoleCommand{

    @Override
    public void execCommand(Scanner scanner, Channel channel) {
        System.out.println("please input your name : ");
        String userName = scanner.next();

        System.out.println("please input your password : ");
        String password = scanner.next();

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName(userName);
        loginRequestPacket.setPassword(password);

        channel.writeAndFlush(loginRequestPacket);

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
