package com.selflearn.nettyim.protocol.packet.response;

import com.selflearn.nettyim.protocol.Command;
import com.selflearn.nettyim.protocol.Packet;
import lombok.Data;

import java.util.List;

/**
 * Created by coding-dong on 2018/11/17.
 */
@Data
public class ListGroupResponsePacket extends Packet{

    private List<String> memberList;

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_RESPONSE_COMMAND.getType();
    }
}
