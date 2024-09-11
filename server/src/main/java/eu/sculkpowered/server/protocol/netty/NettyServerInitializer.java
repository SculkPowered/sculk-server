package eu.sculkpowered.server.protocol.netty;

import static eu.sculkpowered.server.util.Constants.FRAME_DECODER;
import static eu.sculkpowered.server.util.Constants.FRAME_ENCODER;
import static eu.sculkpowered.server.util.Constants.HANDLER;
import static eu.sculkpowered.server.util.Constants.MINECRAFT_DECODER;
import static eu.sculkpowered.server.util.Constants.MINECRAFT_ENCODER;

import eu.sculkpowered.server.SculkServer;
import eu.sculkpowered.server.protocol.SculkConnection;
import eu.sculkpowered.server.protocol.netty.codec.MinecraftDecoder;
import eu.sculkpowered.server.protocol.netty.codec.MinecraftEncoder;
import eu.sculkpowered.server.protocol.netty.codec.VarIntFrameDecoder;
import eu.sculkpowered.server.protocol.netty.codec.VarIntLengthEncoder;
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
        .addLast(MINECRAFT_DECODER, new MinecraftDecoder())
        .addLast(MINECRAFT_ENCODER, new MinecraftEncoder())
        .addLast(HANDLER, new SculkConnection(this.server, channel));
  }
}
