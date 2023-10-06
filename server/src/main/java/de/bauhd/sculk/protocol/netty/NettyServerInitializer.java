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
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

import static de.bauhd.sculk.util.Constants.*;

public final class NettyServerInitializer extends ChannelInitializer<Channel> {

    public static final boolean IS_JAVA_CIPHER = Natives.cipher.get() == JavaVelocityCipher.FACTORY;

    private final SculkServer server;

    public NettyServerInitializer(final SculkServer server) {
        this.server = server;
    }

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                .addLast(FRAME_DECODER, new VarIntFrameDecoder())
                .addLast(FRAME_ENCODER, VarIntLengthEncoder.INSTANCE)
                .addLast(MINECRAFT_DECODER, new MinecraftDecoder(Protocol.Direction.SERVERBOUND))
                .addLast(MINECRAFT_ENCODER, new MinecraftEncoder(Protocol.Direction.CLIENTBOUND))
                .addLast(HANDLER, new SculkConnection(this.server, channel));
    }
}
