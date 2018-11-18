package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.protocol.Packet;
import com.selflearn.nettyim.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by coding-dong on 2018/11/5.
 */
public class PacketDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Packet packet = PacketCodec.INSTANCE.decode(in);

        out.add(packet);
    }
}
