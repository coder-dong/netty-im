package com.selflearn.nettyim.protocol.packet.request;

import com.selflearn.nettyim.protocol.Command;
import com.selflearn.nettyim.protocol.Packet;
import lombok.Data;

import java.util.List;

/**
 * Created by coding-dong on 2018/11/14.
 */
@Data
public class CreateGroupRequestPacket extends Packet{
    private List<String> userIdList;

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_REQUEST_COMMAND.getType();
    }
}
