package eu.sculkpowered.server.protocol.netty.codec;

import static eu.sculkpowered.server.util.Constants.IS_JAVA_CIPHER;

import com.velocitypowered.natives.compression.VelocityCompressor;
import com.velocitypowered.natives.util.MoreByteBufUtils;
import eu.sculkpowered.server.MinecraftConfig;
import eu.sculkpowered.server.protocol.packet.VarInt;
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
      VarInt.write(out, size + 1);
      VarInt.write(out, 0);
      out.writeBytes(buf);
    } else {
      out.writeMedium(8421376);
      VarInt.write(out, size);
      final var compatible = MoreByteBufUtils.ensureCompatible(ctx.alloc(), this.compressor, buf);
      try {
        this.compressor.deflate(compatible, out);
      } finally {
        compatible.release();
      }
      final var length = out.readableBytes() - 3;
      final var offset = out.writerIndex();
      out.writerIndex(0);
      out.writeMedium(
          (length & 0x7F | 0x80) << 16 | ((length >>> 7) & 0x7F | 0x80) << 8 | (length >>> 14));
      out.writerIndex(offset);
    }
  }

  @Override
  protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, ByteBuf buf, boolean preferDirect) {
    var size = buf.readableBytes();
    if (size < this.threshold) {
      size += 1 + VarInt.length(size + 1);
      return IS_JAVA_CIPHER ? ctx.alloc().heapBuffer(size)
          : ctx.alloc().directBuffer(size);
    } else {
      size += 2 + VarInt.length(size);
      return MoreByteBufUtils.preferredBuffer(ctx.alloc(), this.compressor, size);
    }
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) {
    this.compressor.close();
  }
}
