package de.bauhd.minecraft.server.protocol.netty.codec;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.State;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.MessageToByteEncoder;

public final class MinecraftEncoder extends MessageToByteEncoder<Packet> {

    private final Protocol.Direction direction;
    private State state;
    private State.PacketRegistry registry;

    public MinecraftEncoder(final Protocol.Direction direction) {
        this.direction = direction;
        this.state = State.HANDSHAKE;
        this.registry = direction.getRegistry(this.state);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, Buffer buf) {
        PacketUtils.writeVarInt(buf, this.registry.getPacketId(packet));
        packet.encode(new de.bauhd.minecraft.server.protocol.Buffer(buf));

        //System.out.println("encode " + packet + " - " + this.registry.getPacketId(packet));
    }

    @Override
    protected Buffer allocateBuffer(ChannelHandlerContext channelHandlerContext, Packet packet) {
        return channelHandlerContext.bufferAllocator().allocate(0);
    }

    public void set(final State state) {
        this.state = state;
        this.registry = this.direction.getRegistry(state);
    }

    public void setState(final State state) {
        this.set(state);
    }
}
