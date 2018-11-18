package com.selflearn.nettyim.protocol.packet.request;

import com.selflearn.nettyim.protocol.Command;
import com.selflearn.nettyim.protocol.Packet;
import lombok.Data;

/**
 * Created by coding-dong on 2018/11/1.
 */
@Data
public class LoginRequestPacket extends Packet{

    private String userId;

    private String userName;

    private String password;

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST_COMMAND.getType();
    }
}
