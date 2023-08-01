package de.bauhd.minecraft.server.protocol.netty;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.MineConnection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftDecoder;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftEncoder;
import de.bauhd.minecraft.server.protocol.netty.codec.VarIntFrameDecoder;
import de.bauhd.minecraft.server.protocol.netty.codec.VarIntLengthEncoder;
import io.netty5.channel.Channel;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.channel.ChannelInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class NettyServerInitializer extends ChannelInitializer<Channel> {

    private static final Logger LOGGER = LogManager.getLogger(NettyServerInitializer.class);
    private final AdvancedMinecraftServer server;

    public NettyServerInitializer(final AdvancedMinecraftServer server) {
        this.server = server;
    }

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                .addLast("frame-decoder", new VarIntFrameDecoder())
                .addLast("frame-encoder", VarIntLengthEncoder.INSTANCE)
                .addLast("minecraft-decoder", new MinecraftDecoder(Protocol.Direction.SERVERBOUND))
                .addLast("minecraft-encoder", new MinecraftEncoder(Protocol.Direction.CLIENTBOUND))
                .addLast("handler", new MineConnection(this.server, channel));
    }

    @Override
    public void channelExceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error(cause);
    }
}
