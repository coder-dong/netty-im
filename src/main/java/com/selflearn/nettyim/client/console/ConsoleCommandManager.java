package com.selflearn.nettyim.client.console;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by coding-dong on 2018/11/17.
 */
public class ConsoleCommandManager implements ConsoleCommand{

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new ConcurrentHashMap<>(4);

        consoleCommandMap.put("login", new LoginConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("sendToOne", new SendToOneConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroup", new ListGroupConsoleCommand());
        consoleCommandMap.put("messageGroup", new MessageGroupConsoleCommand());
    }

    @Override
    public void execCommand(Scanner scanner, Channel channel) {
        System.out.println("input your command:");
        String command = scanner.next();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand != null){
            consoleCommand.execCommand(scanner, channel);
        }else {
            System.out.println("command can't support, please input again");
        }
    }
}
