package de.bauhd.minecraft.server.protocol.netty.codec;

import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.MessageToMessageDecoder;

import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class CompressorDecoder extends MessageToMessageDecoder<Buffer> {

    private final Inflater inflater;
    private final byte[] encode = new byte[8192];

    public CompressorDecoder() {
        this.inflater = new Inflater(false);
    }

    @Override
    protected void decodeAndClose(ChannelHandlerContext ctx, Buffer buf) {
        final int size = PacketUtils.readVarInt(buf);
        if (size == 0) {
            ctx.fireChannelRead(buf);
            return;
        }

        try (buf) {
            final var bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes, 0, bytes.length);
            this.inflater.setInput(bytes);
            final var out = ctx.bufferAllocator().allocate(0);
            while (!this.inflater.finished() && this.inflater.getBytesWritten() < size) {
                out.writeBytes(this.encode, 0, this.inflater.inflate(this.encode));
            }
            ctx.fireChannelRead(out);
        } catch (DataFormatException e) {
            throw new RuntimeException(e);
        } finally {
            this.inflater.reset();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        this.inflater.end();
    }
}
