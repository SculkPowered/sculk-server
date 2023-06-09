package de.bauhd.minecraft.server.protocol.netty.codec;

import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.MessageToMessageDecoder;

public final class CipherDecoder extends MessageToMessageDecoder<Buffer> {

    private final JavaCipher cipher;

    public CipherDecoder(final JavaCipher cipher) {
        this.cipher = cipher;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Buffer msg) throws Exception {
        ctx.fireChannelRead(this.cipher.process(ctx, msg));
    }
}
