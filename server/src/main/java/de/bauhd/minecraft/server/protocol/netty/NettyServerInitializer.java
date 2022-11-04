package de.bauhd.minecraft.server.protocol.netty;

import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftDecoder;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftEncoder;
import de.bauhd.minecraft.server.protocol.netty.codec.VarIntFrameDecoder;
import de.bauhd.minecraft.server.protocol.netty.codec.VarIntLengthEncoder;
import io.netty5.channel.Channel;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.channel.ChannelInitializer;

public final class NettyServerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                .addLast("frame-decoder", new VarIntFrameDecoder())
                .addLast("frame-encoder", VarIntLengthEncoder.INSTANCE)
                .addLast("minecraft-decoder", new MinecraftDecoder(Protocol.Direction.SERVERBOUND))
                .addLast("minecraft-encoder", new MinecraftEncoder(Protocol.Direction.CLIENTBOUND))
                .addLast("handler", new Connection(channel));
    }

    @Override
    public void channelExceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
    }
}
