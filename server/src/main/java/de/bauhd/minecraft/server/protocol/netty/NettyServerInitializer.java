package de.bauhd.minecraft.server.protocol.netty;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.MineConnection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftDecoder;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftEncoder;
import de.bauhd.minecraft.server.protocol.netty.codec.VarIntFrameDecoder;
import de.bauhd.minecraft.server.protocol.netty.codec.VarIntLengthEncoder;
import io.netty5.channel.Channel;
import io.netty5.channel.ChannelInitializer;

import static de.bauhd.minecraft.server.util.Constant.*;

public final class NettyServerInitializer extends ChannelInitializer<Channel> {

    private final AdvancedMinecraftServer server;

    public NettyServerInitializer(final AdvancedMinecraftServer server) {
        this.server = server;
    }

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                .addLast(FRAME_DECODER, new VarIntFrameDecoder())
                .addLast(FRAME_ENCODER, VarIntLengthEncoder.INSTANCE)
                .addLast(MINECRAFT_DECODER, new MinecraftDecoder(Protocol.Direction.SERVERBOUND))
                .addLast(MINECRAFT_ENCODER, new MinecraftEncoder(Protocol.Direction.CLIENTBOUND))
                .addLast(HANDLER, new MineConnection(this.server, channel));
    }
}
