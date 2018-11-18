package com.selflearn.nettyim.protocol.packet.response;

import com.selflearn.nettyim.protocol.Command;
import com.selflearn.nettyim.protocol.Packet;
import lombok.Data;

import java.util.List;

/**
 * Created by coding-dong on 2018/11/15.
 */
@Data
public class CreateGroupResponsePacket extends Packet{
    private long groupId;

    private List<String> memberList;

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE_COMMAND.getType();
    }
}
