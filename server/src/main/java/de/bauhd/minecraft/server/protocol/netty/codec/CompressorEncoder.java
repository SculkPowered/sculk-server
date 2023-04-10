package de.bauhd.minecraft.server.protocol.netty.codec;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.MessageToByteEncoder;

import java.util.zip.Deflater;

public final class CompressorEncoder extends MessageToByteEncoder<Buffer> {

    private final Deflater deflater;

    public CompressorEncoder(final AdvancedMinecraftServer server) {
        this.deflater = new Deflater(server.getConfiguration().compressionThreshold(), false);
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Buffer buffer, Buffer buffer2) {

    }

    @Override
    protected Buffer allocateBuffer(ChannelHandlerContext channelHandlerContext, Buffer buffer) {
        return channelHandlerContext.bufferAllocator().allocate(0);
    }
}
