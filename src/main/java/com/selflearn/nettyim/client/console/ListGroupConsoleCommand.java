package com.selflearn.nettyim.client.console;

import com.selflearn.nettyim.protocol.packet.request.ListGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class ListGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void execCommand(Scanner scanner, Channel channel) {
        System.out.println("input you want list group id:");

        String groupId = scanner.next();

        ListGroupRequestPacket listGroupRequestPacket = new ListGroupRequestPacket();
        listGroupRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(listGroupRequestPacket);
    }
}
