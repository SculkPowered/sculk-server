package de.bauhd.sculk.protocol.netty.codec;

import com.velocitypowered.natives.compression.VelocityCompressor;
import com.velocitypowered.natives.util.MoreByteBufUtils;
import de.bauhd.sculk.MinecraftConfig;
import de.bauhd.sculk.protocol.netty.NettyServerInitializer;
import de.bauhd.sculk.protocol.packet.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class CompressorEncoder extends MessageToByteEncoder<ByteBuf> {

    private final VelocityCompressor compressor;
    private final int threshold;

    public CompressorEncoder(final VelocityCompressor compressor, final MinecraftConfig config) {
        this.compressor = compressor;
        this.threshold = config.compressionThreshold();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf buf, ByteBuf out) throws Exception {
        final var size = buf.readableBytes();
        if (size < this.threshold) { // under threshold - so nothing to do
            PacketUtils.writeVarInt(out, size + 1);
            PacketUtils.writeVarInt(out, 0);
            out.writeBytes(buf);
        } else {
            out.writeMedium(8421376);
            PacketUtils.writeVarInt(out, size);
            final var compatible = MoreByteBufUtils.ensureCompatible(ctx.alloc(), this.compressor, buf);
            try {
                this.compressor.deflate(compatible, out);
            } finally {
                compatible.release();
            }
            final var length = out.readableBytes() - 3;
            final var offset = out.writerIndex();
            out.writerIndex(0);
            out.writeMedium((length & 0x7F | 0x80) << 16 | ((length >>> 7) & 0x7F | 0x80) << 8 | (length >>> 14));
            out.writerIndex(offset);
        }
    }

    @Override
    protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, ByteBuf buf, boolean preferDirect) {
        var size = buf.readableBytes();
        if (size < this.threshold) {
            size += 1 + PacketUtils.varIntLength(size + 1);
            return NettyServerInitializer.IS_JAVA_CIPHER ? ctx.alloc().heapBuffer(size) : ctx.alloc().directBuffer(size);
        } else {
            size += 2 + PacketUtils.varIntLength(size);
            return MoreByteBufUtils.preferredBuffer(ctx.alloc(), this.compressor, size);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        this.compressor.close();
    }
}
