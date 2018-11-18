package com.selflearn.nettyim.protocol.packet.response;

import com.selflearn.nettyim.protocol.Command;
import com.selflearn.nettyim.protocol.Packet;
import lombok.Data;

import java.util.Map;

/**
 * Created by coding-dong on 2018/11/1.
 */
@Data
public class LoginResponsePacket extends Packet{

    private boolean success;

    private int statusCode;

    private String errorMessage;

    private Map<String, String> data;

    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE_COMMAND.getType();
    }
}
