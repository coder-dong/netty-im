package com.selflearn.nettyim.client.console;

import com.selflearn.nettyim.protocol.packet.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class QuitGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void execCommand(Scanner scanner, Channel channel) {
        System.out.println("input you want quit group id:");

        String groupId = scanner.next();

        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
