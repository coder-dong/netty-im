package com.selflearn.nettyim.protocol.packet.request;

import com.selflearn.nettyim.protocol.Command;
import com.selflearn.nettyim.protocol.Packet;
import lombok.Data;

/**
 * Created by coding-dong on 2018/11/17.
 */
@Data
public class ListGroupRequestPacket extends Packet{

    private String groupId;

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_REQUEST_COMMAND.getType();
    }
}
