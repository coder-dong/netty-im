package com.selflearn.nettyim.client.console;

import com.selflearn.nettyim.protocol.packet.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class SendToOneConsoleCommand implements ConsoleCommand{

    @Override
    public void execCommand(Scanner scanner, Channel channel) {
        System.out.println("please input you send to userId");
        long userId = scanner.nextLong();

        System.out.println("please input you send to message");
        String message = scanner.next();

        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
        messageRequestPacket.setToUserId(userId);
        messageRequestPacket.setData(message);

        channel.writeAndFlush(messageRequestPacket);
    }
}
