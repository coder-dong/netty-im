package com.selflearn.nettyim.client.console;

import com.selflearn.nettyim.protocol.packet.request.MessageGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class MessageGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void execCommand(Scanner scanner, Channel channel) {
        System.out.println("input you want send message group id:");
        String groupId = scanner.next();

        System.out.println("input you message:");
        String message = scanner.next();

        MessageGroupRequestPacket messageGroupRequestPacket = new MessageGroupRequestPacket();
        messageGroupRequestPacket.setGroupId(groupId);
        messageGroupRequestPacket.setMessage(message);

        channel.writeAndFlush(messageGroupRequestPacket);
    }
}
