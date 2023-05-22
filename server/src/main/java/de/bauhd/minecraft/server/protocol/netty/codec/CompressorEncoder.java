package de.bauhd.minecraft.server.protocol.netty.codec;

import de.bauhd.minecraft.server.MinecraftConfig;
import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.MessageToByteEncoder;

import java.util.zip.Deflater;

public final class CompressorEncoder extends MessageToByteEncoder<Buffer> {

    private final int threshold;
    private final Deflater deflater;
    private final byte[] encode = new byte[8192];

    public CompressorEncoder(final MinecraftConfig config) {
        this.threshold = config.compressionThreshold();
        this.deflater = new Deflater(config.compressionLevel(), false);
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Buffer buf, Buffer out) {
        final var size = buf.readableBytes();
        if (size < this.threshold) { // under threshold - so nothing to do
            PacketUtils.writeVarInt(out, size + 1);
            PacketUtils.writeVarInt(out, 0);
            out.writeBytes(buf);
        } else {
            out.writeMedium(8421376);
            PacketUtils.writeVarInt(out, size);
            final var bytes = new byte[size];
            buf.readBytes(bytes, 0, bytes.length);
            this.deflater.setInput(bytes);
            this.deflater.finish();
            while (!this.deflater.finished()) {
                out.writeBytes(this.encode, 0, this.deflater.deflate(this.encode));
            }
            this.deflater.reset();
            final var length = out.readableBytes() - 3;
            final var offset = out.writerOffset();
            out.writeMedium((length & 0x7F | 0x80) << 16 | ((length >>> 7) & 0x7F | 0x80) << 8 | (length >>> 14));
            out.writerOffset(offset);
        }
    }

    @Override
    protected Buffer allocateBuffer(ChannelHandlerContext channelHandlerContext, Buffer buf) {
        return channelHandlerContext.bufferAllocator().allocate(buf.readableBytes() + 1);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        this.deflater.end();
    }
}
