package de.bauhd.minecraft.server.protocol.netty.codec;

import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.MessageToByteEncoder;

public final class VarIntLengthEncoder extends MessageToByteEncoder<Buffer> {

    public static final VarIntLengthEncoder INSTANCE = new VarIntLengthEncoder();

    @Override
    protected void encode(ChannelHandlerContext ctx, Buffer msg, Buffer out) {
        PacketUtils.writeVarInt(out, msg.readableBytes());
        out.writeBytes(msg);
    }

    @Override
    protected Buffer allocateBuffer(ChannelHandlerContext channelHandlerContext, Buffer buffer) {
        return channelHandlerContext.bufferAllocator()
                .allocate(Integer.numberOfLeadingZeros(buffer.readableBytes()) + buffer.readableBytes());
    }

    @Override
    public boolean isSharable() {
        return true;
    }

}
