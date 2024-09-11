package eu.sculkpowered.server.protocol.netty.codec;

import static eu.sculkpowered.server.util.Constants.IS_JAVA_CIPHER;

import eu.sculkpowered.server.protocol.packet.VarInt;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class VarIntLengthEncoder extends MessageToByteEncoder<ByteBuf> {

  public static final VarIntLengthEncoder INSTANCE = new VarIntLengthEncoder();

  @Override
  protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) {
    VarInt.write(out, msg.readableBytes());
    out.writeBytes(msg);
  }

  @Override
  protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, ByteBuf buf, boolean preferDirect) {
    final var size = VarInt.length(buf.readableBytes()) + buf.readableBytes();
    return IS_JAVA_CIPHER ? ctx.alloc().heapBuffer(size)
        : ctx.alloc().directBuffer(size);
  }

  @Override
  public boolean isSharable() {
    return true;
  }
}
