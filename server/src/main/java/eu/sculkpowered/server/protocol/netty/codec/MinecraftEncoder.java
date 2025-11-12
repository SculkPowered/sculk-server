package eu.sculkpowered.server.protocol.netty.codec;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.State;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.protocol.packet.VarInt;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class MinecraftEncoder extends MessageToByteEncoder<ClientboundPacket> {

  private State.ClientboundRegistry registry;

  @Override
  protected void encode(ChannelHandlerContext ctx, ClientboundPacket packet, ByteBuf buf) {
    VarInt.write(buf, this.registry.packetId(packet));
    packet.encode(new Buffer(buf));
    System.out.println("Encoded " + packet);
  }

  @Override
  protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, ClientboundPacket packet, boolean preferDirect) {
    return ctx.alloc().directBuffer(
        VarInt.length(this.registry.packetId(packet)) + packet.minLength());
  }

  public void setState(final State state) {
    this.registry = state.clientBound;
  }
}
