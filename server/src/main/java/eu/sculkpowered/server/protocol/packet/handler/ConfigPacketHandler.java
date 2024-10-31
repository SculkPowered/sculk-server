package eu.sculkpowered.server.protocol.packet.handler;

import eu.sculkpowered.server.entity.player.SculkPlayer;
import eu.sculkpowered.server.protocol.SculkConnection;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.shared.FinishConfigurationPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.ClientInformationPacket;
import eu.sculkpowered.server.protocol.packet.shared.KeepAlivePacket;
import eu.sculkpowered.server.protocol.packet.shared.CustomPayloadPacket;
import eu.sculkpowered.server.protocol.packet.shared.SelectKnownPacks;

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
  public boolean handle(SelectKnownPacks selectKnownPacks) {
    System.out.println(selectKnownPacks.knownPacks());
    return true;
  }

  @Override
  public boolean handle(KeepAlivePacket keepAlive) {
    return true;
  }
}