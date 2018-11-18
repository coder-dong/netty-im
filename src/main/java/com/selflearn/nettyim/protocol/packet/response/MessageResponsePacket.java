package com.selflearn.nettyim.protocol.packet.response;

import com.selflearn.nettyim.protocol.Command;
import com.selflearn.nettyim.protocol.Packet;
import lombok.Data;

import java.util.Map;

/**
 * Created by coding-dong on 2018/11/4.
 */
@Data
public class MessageResponsePacket extends Packet{

    private boolean success;

    private int statusCode;

    private String errorMessage;

    private Map<String, String> data;

    @Override
    public byte getCommand() {
        return Command.MESSAGE_RESPONSE_COMMAND.getType();
    }
}
