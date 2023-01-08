package de.bauhd.minecraft.server.protocol;

import de.bauhd.minecraft.server.protocol.netty.codec.JavaCipher;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.MessageToMessageEncoder;

import java.util.List;

public final class CipherEncoder extends MessageToMessageEncoder<Buffer> {

    private final JavaCipher cipher;

    public CipherEncoder(final JavaCipher cipher) {
        this.cipher = cipher;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Buffer msg, List<Object> out) throws Exception {
        out.add(this.cipher.process(ctx, msg));
    }
}
