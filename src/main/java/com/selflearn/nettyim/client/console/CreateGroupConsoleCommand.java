package com.selflearn.nettyim.client.console;

import com.selflearn.nettyim.protocol.packet.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;
import io.netty.util.internal.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by coding-dong on 2018/11/16.
 */
public class CreateGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void execCommand(Scanner scanner, Channel channel) {

        List<String> userIdList;
        do {
            System.out.println("input group member id , please split comma");
            String membersStr = scanner.next();

            userIdList = Arrays.stream(membersStr.split(","))
                    .filter(id -> !StringUtil.isNullOrEmpty(id) && Long.parseLong(id) >= 0)
                    .collect(Collectors.toList());

            if (userIdList != null && !userIdList.isEmpty()){
                break;
            }
        }while (true);

        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserIdList(userIdList);

        channel.writeAndFlush(createGroupRequestPacket);

        System.out.println("channel[" + channel.id().toString() + "] has send create group command");
    }
}
