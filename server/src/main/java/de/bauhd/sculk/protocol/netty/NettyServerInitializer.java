package de.bauhd.sculk.protocol.netty;

import com.velocitypowered.natives.encryption.JavaVelocityCipher;
import com.velocitypowered.natives.util.Natives;
import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.protocol.SculkConnection;
import de.bauhd.sculk.protocol.Protocol;
import de.bauhd.sculk.protocol.netty.codec.MinecraftDecoder;
import de.bauhd.sculk.protocol.netty.codec.MinecraftEncoder;
import de.bauhd.sculk.protocol.netty.codec.VarIntFrameDecoder;
import de.bauhd.sculk.protocol.netty.codec.VarIntLengthEncoder;
import de.bauhd.sculk.util.Constant;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public final class NettyServerInitializer extends ChannelInitializer<Channel> {

    public static final boolean IS_JAVA_CIPHER = Natives.cipher.get() == JavaVelocityCipher.FACTORY;

    private final SculkServer server;

    public NettyServerInitializer(final SculkServer server) {
        this.server = server;
    }

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                .addLast(Constant.FRAME_DECODER, new VarIntFrameDecoder())
                .addLast(Constant.FRAME_ENCODER, VarIntLengthEncoder.INSTANCE)
                .addLast(Constant.MINECRAFT_DECODER, new MinecraftDecoder(Protocol.Direction.SERVERBOUND))
                .addLast(Constant.MINECRAFT_ENCODER, new MinecraftEncoder(Protocol.Direction.CLIENTBOUND))
                .addLast(Constant.HANDLER, new SculkConnection(this.server, channel));
    }
}
