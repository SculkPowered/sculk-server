package io.github.sculkpowered.server.protocol.netty;

import static io.github.sculkpowered.server.util.Constants.FRAME_DECODER;
import static io.github.sculkpowered.server.util.Constants.FRAME_ENCODER;
import static io.github.sculkpowered.server.util.Constants.HANDLER;
import static io.github.sculkpowered.server.util.Constants.MINECRAFT_DECODER;
import static io.github.sculkpowered.server.util.Constants.MINECRAFT_ENCODER;

import com.velocitypowered.natives.encryption.JavaVelocityCipher;
import com.velocitypowered.natives.util.Natives;
import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.protocol.Protocol;
import io.github.sculkpowered.server.protocol.SculkConnection;
import io.github.sculkpowered.server.protocol.netty.codec.MinecraftDecoder;
import io.github.sculkpowered.server.protocol.netty.codec.MinecraftEncoder;
import io.github.sculkpowered.server.protocol.netty.codec.VarIntFrameDecoder;
import io.github.sculkpowered.server.protocol.netty.codec.VarIntLengthEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

final class NettyServerInitializer extends ChannelInitializer<Channel> {

  private final SculkServer server;

  NettyServerInitializer(final SculkServer server) {
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
