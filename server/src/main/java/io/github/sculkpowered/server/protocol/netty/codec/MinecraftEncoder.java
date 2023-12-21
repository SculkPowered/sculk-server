package io.github.sculkpowered.server.protocol.netty.codec;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.Protocol;
import io.github.sculkpowered.server.protocol.State;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class MinecraftEncoder extends MessageToByteEncoder<Packet> {

  private final Protocol.Direction direction;
  private State.PacketRegistry registry;

  public MinecraftEncoder(final Protocol.Direction direction) {
    this.direction = direction;
  }

  @Override
  protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf buf) {
    PacketUtils.writeVarInt(buf, this.registry.getPacketId(packet));
    packet.encode(new Buffer(buf));
    System.out.println("encode " + packet);
  }

  @Override
  protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, Packet packet, boolean preferDirect) {
    return ctx.alloc().directBuffer(
        PacketUtils.varIntLength(this.registry.getPacketId(packet)) + packet.minLength());
  }

  public void setState(final State state) {
    this.registry = this.direction.getRegistry(state);
  }
}
