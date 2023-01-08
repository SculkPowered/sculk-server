package de.bauhd.minecraft.server.protocol.netty.codec;

import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.MessageToMessageEncoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public final class JavaCipher extends MessageToMessageEncoder<Buffer> {

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

    @Override
    protected void encode(ChannelHandlerContext ctx, Buffer msg, List<Object> out) throws Exception {
        final var bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes, 0, bytes.length);
        this.cipher.update(bytes, 0, bytes.length, bytes, 0);
        out.add(ctx.bufferAllocator().copyOf(bytes));
    }
}
