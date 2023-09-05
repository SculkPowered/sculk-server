package de.bauhd.sculk.protocol.netty.codec;

import de.bauhd.sculk.protocol.packet.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public final class VarIntFrameDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) {
        if (!ctx.channel().isActive()) {
            buf.clear();
            return;
        }

        final var readerIndex = buf.readerIndex();
        final var length = PacketUtils.readVarInt(buf);

        if (readerIndex == buf.readerIndex()) {
            buf.readerIndex(readerIndex);
            return;
        }

        if (length <= 0) {
            if (buf.readableBytes() > 0) {
                buf.skipBytes(buf.readableBytes());
            }
            buf.clear();
            return;
        }

        if (buf.readableBytes() >= length) {
            out.add(buf.retainedSlice(buf.readerIndex(), length));
            buf.skipBytes(length);
        } else {
            buf.readerIndex(readerIndex);
        }
    }
}
