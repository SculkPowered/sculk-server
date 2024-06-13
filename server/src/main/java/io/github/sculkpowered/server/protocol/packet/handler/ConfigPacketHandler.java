package io.github.sculkpowered.server.protocol.packet.handler;

import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.protocol.SculkConnection;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.shared.FinishConfigurationPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ClientInformationPacket;
import io.github.sculkpowered.server.protocol.packet.shared.KeepAlivePacket;
import io.github.sculkpowered.server.protocol.packet.shared.CustomPayloadPacket;

public final class ConfigPacketHandler extends PacketHandler {

  private final SculkConnection connection;
  private final SculkPlayer player;

  public ConfigPacketHandler(final SculkConnection connection, final SculkPlayer player) {
    this.connection = connection;
    this.player = player;
  }

  @Override
  public boolean handle(ClientInformationPacket clientInformation) {
    this.player.handleClientInformation(clientInformation);
    return true;
  }

  @Override
  public boolean handle(CustomPayloadPacket customPayload) {
    this.player.handlePluginMessage(customPayload);
    return true;
  }

  @Override
  public boolean handle(FinishConfigurationPacket finishConfiguration) {
    this.connection.play();
    return true;
  }

  @Override
  public boolean handle(KeepAlivePacket keepAlive) {
    return true;
  }
}