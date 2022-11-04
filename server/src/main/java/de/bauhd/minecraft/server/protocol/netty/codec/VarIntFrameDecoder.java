package de.bauhd.minecraft.server.protocol.netty.codec;

import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.ByteToMessageDecoder;

public final class VarIntFrameDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, Buffer buf) {
        if (!ctx.channel().isActive()) {
            buf.close();
            return;
        }

        final int readerIndex = buf.readerOffset();
        final int length = PacketUtils.readVarInt(buf);

        if (readerIndex == buf.readerOffset()) {
            buf.readerOffset(readerIndex);
            return;
        }

        if (length <= 0) {
            if (buf.readableBytes() > 0) {
                buf.skipReadableBytes(buf.readableBytes());
            }
            return;
        }

        if (buf.readableBytes() >= length) {
            ctx.fireChannelRead(buf.copy(buf.readerOffset(), length, true));
            buf.skipReadableBytes(length);
        } else {
            buf.readerOffset(readerIndex);
        }
    }
}
