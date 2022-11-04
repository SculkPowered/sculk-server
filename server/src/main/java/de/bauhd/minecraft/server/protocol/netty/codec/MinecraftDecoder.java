package de.bauhd.minecraft.server.protocol.netty.codec;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.State;
import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import de.bauhd.minecraft.server.protocol.packet.play.KeepAlive;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandler;
import io.netty5.channel.ChannelHandlerContext;

public final class MinecraftDecoder implements ChannelHandler {

    private final Protocol.Direction direction;
    private State state;
    private State.PacketRegistry.ProtocolRegistry registry;

    public MinecraftDecoder(Protocol.Direction direction) {
        this.direction = direction;
        this.state = State.HANDSHAKE;
        this.registry = direction.getRegistry(this.state, Protocol.Version.MINIMUM);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) {
        if (message instanceof Buffer buf) {
            if (!ctx.channel().isActive() || buf.readableBytes() <= 0) {
                buf.close();
                return;
            }

            final var id = PacketUtils.readVarInt(buf);
            final var packet = this.registry.createPacket(id);
            if (packet == null) {
                //System.out.println("unknown packet id " + id + " " + this.registry.version);
            } else {
                try (buf) {
                    final var minLength = packet.minLength();
                    final var maxLength = packet.maxLength();
                    if (maxLength != -1 && buf.readableBytes() > maxLength) {
                        System.out.println("overflow");
                    }
                    if (buf.readableBytes() < minLength) {
                        System.out.println("underflow");
                    }

                    try {
                        packet.decode(buf, this.registry.version);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (buf.readableBytes() > 0) {
                        System.out.println("overflow after decode " + packet);
                    }
                    if (packet.getClass() != KeepAlive.class) System.out.println("decoded " + packet + " - " + id);
                    ctx.fireChannelRead(packet);
                }
            }
        } else {
            ctx.fireChannelRead(message);
        }
    }

    public void set(final State state, final Protocol.Version version) {
        this.state = state;
        this.registry = this.direction.getRegistry(state, version);
    }

    public void setState(final State state) {
        this.set(state, this.registry.version);
    }

}