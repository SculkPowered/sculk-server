package de.bauhd.minecraft.server.protocol.netty.codec;

import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.MessageToMessageDecoder;

import java.util.zip.Inflater;

public final class CompressorDecoder extends MessageToMessageDecoder<Buffer> {

    private final Inflater inflater;

    public CompressorDecoder() {
        this.inflater = new Inflater(false);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Buffer buf) throws Exception {
        final int size = PacketUtils.readVarInt(buf);

        if (size == 0) {
            ctx.fireChannelRead(buf);
            return;
        }


    }
}
