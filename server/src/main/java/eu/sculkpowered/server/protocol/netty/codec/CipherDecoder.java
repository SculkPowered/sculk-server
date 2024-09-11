package eu.sculkpowered.server.protocol.netty.codec;

import com.velocitypowered.natives.encryption.VelocityCipher;
import com.velocitypowered.natives.util.MoreByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;

public final class CipherDecoder extends MessageToMessageDecoder<ByteBuf> {

  private final VelocityCipher cipher;

  public CipherDecoder(final VelocityCipher cipher) {
    this.cipher = cipher;
  }

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) {
    final var compatible = MoreByteBufUtils.ensureCompatible(ctx.alloc(), this.cipher, buf).slice();
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
