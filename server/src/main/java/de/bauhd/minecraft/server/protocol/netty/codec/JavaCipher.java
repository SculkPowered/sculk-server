package de.bauhd.minecraft.server.protocol.netty.codec;

import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public final class JavaCipher {

    private final Cipher cipher;

    public JavaCipher(final SecretKey secretKey, final int mode) {
        try {
            this.cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            this.cipher.init(mode, secretKey, new IvParameterSpec(secretKey.getEncoded()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public Buffer process(final ChannelHandlerContext ctx, final Buffer buf) throws Exception {
        final var bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes, 0, bytes.length);
        this.cipher.update(bytes, 0, bytes.length, bytes, 0);
        return ctx.bufferAllocator().copyOf(bytes);
    }
}
