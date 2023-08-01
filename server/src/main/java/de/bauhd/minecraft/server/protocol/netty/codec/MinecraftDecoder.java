package de.bauhd.minecraft.server.protocol.netty.codec;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.State;
import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import io.netty5.buffer.Buffer;
import io.netty5.channel.ChannelHandler;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.handler.codec.DecoderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MinecraftDecoder implements ChannelHandler {

    private static final Logger LOGGER = LogManager.getLogger(MinecraftDecoder.class);

    private final Protocol.Direction direction;
    private State.PacketRegistry registry;

    public MinecraftDecoder(Protocol.Direction direction) {
        this.direction = direction;
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
                LOGGER.warn("Unknown packet id 0x" + Integer.toHexString(id));
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
                        packet.decode(new de.bauhd.minecraft.server.protocol.Buffer(buf));
                    } catch (Exception e) {
                        LOGGER.error("Error during decoding of " + packet.getClass(), e);
                    }
                    if (buf.readableBytes() > 0) {
                        throw new DecoderException("Overflow after decode packet " +
                                packet.getClass().getSimpleName() + " (length = " + buf.readableBytes() + ")");
                    }
                    //System.out.println("decoded " + packet + " - " + id);
                    ctx.fireChannelRead(packet);
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