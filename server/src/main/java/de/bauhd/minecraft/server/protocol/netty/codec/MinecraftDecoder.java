package de.bauhd.minecraft.server.protocol.netty.codec;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.State;
import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandler;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.DecoderException;

public final class MinecraftDecoder implements ChannelHandler {

    private final Protocol.Direction direction;
    private State state;
    private State.PacketRegistry.ProtocolRegistry registry;

    public MinecraftDecoder(Protocol.Direction direction) {
        this.direction = direction;
        this.state = State.HANDSHAKE;
        this.registry = direction.getRegistry(this.state, Protocol.Version.MINIMUM_VERSION);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) {
        if (message instanceof Buffer buf) {
            if (!ctx.channel().isActive() || buf.readableBytes() <= 0) {
                buf.close();
                return;
            }

            final var offset = buf.readerOffset();
            final var id = PacketUtils.readVarInt(buf);
            final var packet = this.registry.createPacket(id);
            if (packet == null) {
                buf.readerOffset(offset);
                ctx.fireChannelRead(message);
                System.out.println("unknown packet id " + Integer.toHexString(id) + " " + this.registry.version);
            } else {
                try (buf) {
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
                        packet.decode(new de.bauhd.minecraft.server.protocol.Buffer(buf), this.registry.version);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (buf.readableBytes() > 0) {
                        throw new DecoderException("Overflow after decode packet " +
                                packet.getClass().getSimpleName() + " (length = " + buf.readableBytes() + ")");
                    }
                    System.out.println("decoded " + packet + " - " + id);
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