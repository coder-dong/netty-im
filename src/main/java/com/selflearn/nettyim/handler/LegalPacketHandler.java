package com.selflearn.nettyim.handler;

import com.selflearn.nettyim.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by coding-dong on 2018/11/7.
 */
public class LegalPacketHandler extends LengthFieldBasedFrameDecoder{

    public LegalPacketHandler(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        int anInt = in.getInt(in.readerIndex());
        System.out.println(anInt);
        if (in.getInt(in.readerIndex()) != PacketCodec.MAGIC_NUM){
            System.out.println("illegal message packet, close channel");
            ctx.channel().close();
            return null;
        }else {
            return super.decode(ctx, in);
        }
    }
}
