package com.selflearn.nettyim.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created by coding-dong on 2018/11/16.
 */
public interface ConsoleCommand {

    void execCommand(Scanner scanner, Channel channel);
}
