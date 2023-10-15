package de.bauhd.sculk.protocol.netty.codec;

import de.bauhd.sculk.protocol.netty.NettyServerInitializer;
import de.bauhd.sculk.protocol.packet.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class VarIntLengthEncoder extends MessageToByteEncoder<ByteBuf> {

  public static final VarIntLengthEncoder INSTANCE = new VarIntLengthEncoder();

  @Override
  protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) {
    PacketUtils.writeVarInt(out, msg.readableBytes());
    out.writeBytes(msg);
  }

  @Override
  protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, ByteBuf buf, boolean preferDirect) {
    final var size = PacketUtils.varIntLength(buf.readableBytes()) + buf.readableBytes();
    return NettyServerInitializer.IS_JAVA_CIPHER ? ctx.alloc().heapBuffer(size)
        : ctx.alloc().directBuffer(size);
  }

  @Override
  public boolean isSharable() {
    return true;
  }
}
