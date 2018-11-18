package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.protocol.Packet;
import com.selflearn.nettyim.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by coding-dong on 2018/11/6.
 */
public class PacketEncoder extends MessageToByteEncoder<Packet>{

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodec.INSTANCE.encode(out, msg);
    }
}
