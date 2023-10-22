package io.github.sculkpowered.server.protocol.packet.handler;

import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.protocol.SculkConnection;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.config.FinishConfiguration;
import io.github.sculkpowered.server.protocol.packet.play.ClientInformation;
import io.github.sculkpowered.server.protocol.packet.play.KeepAlive;
import io.github.sculkpowered.server.protocol.packet.play.PluginMessage;

public final class ConfigPacketHandler extends PacketHandler {

  private final SculkConnection connection;
  private final SculkPlayer player;

  public ConfigPacketHandler(final SculkConnection connection, final SculkPlayer player) {
    this.connection = connection;
    this.player = player;
  }

  @Override
  public boolean handle(ClientInformation clientInformation) {
    this.player.handleClientInformation(clientInformation);
    return true;
  }

  @Override
  public boolean handle(PluginMessage pluginMessage) {
    this.player.handlePluginMessage(pluginMessage);
    return true;
  }

  @Override
  public boolean handle(FinishConfiguration finishConfiguration) {
    this.connection.play();
    return true;
  }

  @Override
  public boolean handle(KeepAlive keepAlive) {
    return true;
  }
}