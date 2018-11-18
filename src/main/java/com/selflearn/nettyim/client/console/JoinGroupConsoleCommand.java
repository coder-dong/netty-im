package com.selflearn.nettyim.client.console;

import com.selflearn.nettyim.protocol.packet.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class JoinGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void execCommand(Scanner scanner, Channel channel) {
        System.out.println("input you want join group id:");
        String groupId = scanner.next();

        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
        joinGroupRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
