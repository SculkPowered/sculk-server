package de.bauhd.sculk.protocol.netty.codec;

import com.velocitypowered.natives.compression.VelocityCompressor;
import com.velocitypowered.natives.util.MoreByteBufUtils;
import de.bauhd.sculk.protocol.packet.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public final class CompressorDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final VelocityCompressor compressor;

    public CompressorDecoder(final VelocityCompressor compressor) {
        this.compressor = compressor;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        final int size = PacketUtils.readVarInt(buf);
        if (size == 0) {
            out.add(buf.retain());
            return;
        }

        final var compatible = MoreByteBufUtils.ensureCompatible(ctx.alloc(), this.compressor, buf);
        final var uncompressed = MoreByteBufUtils.preferredBuffer(ctx.alloc(), this.compressor, size);
        try {
            this.compressor.inflate(compatible, uncompressed, size);
            out.add(uncompressed);
        } catch (Exception e) {
            uncompressed.release();
            throw e;
        } finally {
            compatible.release();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        this.compressor.close();
    }
}
