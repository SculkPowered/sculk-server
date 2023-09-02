package de.bauhd.sculk.protocol.netty.codec;

import com.velocitypowered.natives.encryption.VelocityCipher;
import com.velocitypowered.natives.util.MoreByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public final class CipherEncoder extends MessageToMessageEncoder<ByteBuf> {

    private final VelocityCipher cipher;

    public CipherEncoder(final VelocityCipher cipher) {
        this.cipher = cipher;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        final var compatible = MoreByteBufUtils.ensureCompatible(ctx.alloc(), this.cipher, msg);
        try {
            this.cipher.process(compatible);
            out.add(compatible);
        } catch (Exception e) {
            compatible.release();
            throw e;
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        this.cipher.close();
    }
}
