package com.selflearn.nettyim.protocol.packet.request;

import com.selflearn.nettyim.protocol.Command;
import com.selflearn.nettyim.protocol.Packet;
import lombok.Data;

/**
 * Created by coding-dong on 2018/11/2.
 */
@Data
public class MessageRequestPacket extends Packet {

    private String data;

    private long toUserId;

    @Override
    public byte getCommand() {
        return Command.MESSAGE_REQUEST_COMMAND.getType();
    }
}
