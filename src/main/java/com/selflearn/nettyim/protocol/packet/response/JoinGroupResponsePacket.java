package com.selflearn.nettyim.protocol.packet.response;

import com.selflearn.nettyim.protocol.Command;
import com.selflearn.nettyim.protocol.Packet;
import lombok.Data;

/**
 * Created by coding-dong on 2018/11/17.
 */
@Data
public class JoinGroupResponsePacket extends Packet{

    private String joinMember;

    @Override
    public byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE_COMMAND.getType();
    }
}
