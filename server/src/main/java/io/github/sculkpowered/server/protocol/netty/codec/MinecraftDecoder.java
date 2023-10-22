package io.github.sculkpowered.server.protocol.netty.codec;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.Protocol;
import io.github.sculkpowered.server.protocol.State;
import io.github.sculkpowered.server.protocol.packet.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.DecoderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class MinecraftDecoder extends ChannelInboundHandlerAdapter {

  private static final Logger LOGGER = LogManager.getLogger(MinecraftDecoder.class);

  private final Protocol.Direction direction;
  private State.PacketRegistry registry;

  public MinecraftDecoder(Protocol.Direction direction) {
    this.direction = direction;
  }

  @Override
  public void channelRead(@NotNull ChannelHandlerContext ctx, @NotNull Object message) {
    if (message instanceof ByteBuf buf) {
      if (!ctx.channel().isActive() || buf.readableBytes() <= 0) {
        buf.release();
        return;
      }

      final var offset = buf.readerIndex();
      final var id = PacketUtils.readVarInt(buf);
      final var packet = this.registry.createPacket(id);
      if (packet == null) {
        buf.readerIndex(offset);
        ctx.fireChannelRead(message);
        LOGGER.warn("Unknown packet id 0x" + Integer.toHexString(id));
      } else {
        try {
          final var minLength = packet.minLength();
          final var maxLength = packet.maxLength();
          final var length = buf.readableBytes();
          if (maxLength != -1 && length > maxLength) {
            throw new DecoderException("Overflow packet " + packet.getClass().getSimpleName() +
                " (max length = " + maxLength + ", length = " + length + ")");
          }
          if (length < minLength) {
            throw new DecoderException("Underflow packet " + packet.getClass().getSimpleName() +
                " (min length = " + minLength + ", length = " + length + ")");
          }

          try {
            packet.decode(new Buffer(buf));
          } catch (Exception e) {
            LOGGER.error("Error during decoding of " + packet.getClass(), e);
          }
          if (buf.readableBytes() > 0) {
            throw new DecoderException("Overflow after decode packet " +
                packet.getClass().getSimpleName() + " (length = " + buf.readableBytes() + ")");
          }
          ctx.fireChannelRead(packet);
        } finally {
          buf.release();
        }
      }
    } else {
      ctx.fireChannelRead(message);
    }
  }

  public void setState(final State state) {
    this.registry = this.direction.getRegistry(state);
  }
}