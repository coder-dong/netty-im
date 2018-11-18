package com.selflearn.nettyim.protocol;

import com.selflearn.nettyim.protocol.packet.request.*;
import com.selflearn.nettyim.protocol.packet.response.*;
import com.selflearn.nettyim.serializer.Serializer;
import com.selflearn.nettyim.serializer.impl.JsonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by coding-dong on 2018/11/1.
 */
public class PacketCodec {

    public static final int MAGIC_NUM = 0x12345678;

    public static PacketCodec INSTANCE = new PacketCodec();

    Map<Byte, Class<? extends Packet>> commandMap;

    Map<Byte, Serializer> serializerMap;

    private PacketCodec() {
        commandMap = new HashMap<>(2);
        commandMap.put(Command.LOGIN_REQUEST_COMMAND.getType(), LoginRequestPacket.class);
        commandMap.put(Command.LOGIN_RESPONSE_COMMAND.getType(), LoginResponsePacket.class);
        commandMap.put(Command.MESSAGE_REQUEST_COMMAND.getType(), MessageRequestPacket.class);
        commandMap.put(Command.MESSAGE_RESPONSE_COMMAND.getType(), MessageResponsePacket.class);
        commandMap.put(Command.CREATE_GROUP_REQUEST_COMMAND.getType(), CreateGroupRequestPacket.class);
        commandMap.put(Command.CREATE_GROUP_RESPONSE_COMMAND.getType(), CreateGroupResponsePacket.class);

        commandMap.put(Command.JOIN_GROUP_REQUEST_COMMAND.getType(), JoinGroupRequestPacket.class);
        commandMap.put(Command.JOIN_GROUP_RESPONSE_COMMAND.getType(), JoinGroupResponsePacket.class);
        commandMap.put(Command.QUIT_GROUP_REQUEST_COMMAND.getType(), QuitGroupRequestPacket.class);
        commandMap.put(Command.QUIT_GROUP_RESPONSE_COMMAND.getType(), QuitGroupResponsePacket.class);
        commandMap.put(Command.LIST_GROUP_REQUEST_COMMAND.getType(), ListGroupRequestPacket.class);
        commandMap.put(Command.LIST_GROUP_RESPONSE_COMMAND.getType(), ListGroupResponsePacket.class);
        commandMap.put(Command.MESSAGE_GROUP_REQUEST_COMMAND.getType(), MessageGroupRequestPacket.class);
        commandMap.put(Command.MESSAGE_GROUP_RESPONSE_COMMAND.getType(), MessageGroupResponsePacket.class);

        serializerMap = new HashMap<>(2);
        Serializer jsonSerializer = new JsonSerializer();
        serializerMap.put(jsonSerializer.getSerializerAlgorithm(), jsonSerializer);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet){
        byte[] packetBytes = Serializer.DEFAULT.serializer(packet);

        ByteBuf byteBuf = byteBufAllocator.ioBuffer(packetBytes.length);

        byteBuf.writeInt(MAGIC_NUM);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(packetBytes.length);
        byteBuf.writeBytes(packetBytes);

        return byteBuf;
    }

    public ByteBuf encode(ByteBuf out, Packet packet){
        byte[] packetBytes = Serializer.DEFAULT.serializer(packet);


        out.writeInt(MAGIC_NUM);
        out.writeByte(packet.getVersion());
        out.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        out.writeByte(packet.getCommand());
        out.writeInt(packetBytes.length);
        out.writeBytes(packetBytes);

        return out;
    }

    public Packet decode(ByteBuf message){

        message.skipBytes(4);

        message.skipBytes(1);

        byte algorithm = message.readByte();

        byte command = message.readByte();

        Serializer serializer = serializerMap.get(algorithm);

        if (serializer == null){
            return null;
        }

        Class<? extends Packet> packetClass = commandMap.get(command);

        if (packetClass == null){
            return null;
        }


        int messageLength = message.readInt();

        byte[] businessMessage = new byte[messageLength];

        message.readBytes(businessMessage);

        Packet packet = serializer.deSerializer(packetClass, businessMessage);

        return packet;
    }
}
