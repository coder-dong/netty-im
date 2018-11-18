package com.selflearn.nettyim.protocol.packet.response;

import com.selflearn.nettyim.protocol.Command;
import com.selflearn.nettyim.protocol.Packet;
import lombok.Data;

/**
 * Created by coding-dong on 2018/11/17.
 */
@Data
public class QuitGroupResponsePacket extends Packet{

    private String quitMember;

    @Override
    public byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE_COMMAND.getType();
    }
}
