package eu.sculkpowered.server.protocol.packet.handler;

import eu.sculkpowered.server.SculkServer;
import eu.sculkpowered.server.event.connection.ServerPingEvent;
import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.Protocol;
import eu.sculkpowered.server.protocol.SculkConnection;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.shared.StatusPingPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.StatusRequest;
import eu.sculkpowered.server.protocol.packet.clientbound.StatusResponsePacket;
import net.kyori.adventure.text.Component;

public final class StatusPacketHandler extends PacketHandler {

  private final SculkServer server;
  private final SculkConnection connection;

  public StatusPacketHandler(final SculkConnection connection, final SculkServer server) {
    this.connection = connection;
    this.server = server;
  }

  @Override
  public boolean handle(StatusRequest statusRequest) {
    this.server.eventHandler().call(new ServerPingEvent(this.connection))
        .thenAcceptAsync(event -> {
          final var response = event.response();
          if (response != null) {
            if (response.protocol() == Integer.MIN_VALUE) {
              response.protocol(Protocol.VERSION_PROTOCOL);
            }
            if (response.online() == Integer.MIN_VALUE) {
              response.online(this.server.playerCount());
            }
            if (response.description() == null) {
              response.description(Component.empty());
            }
            this.connection.send(new StatusResponsePacket('{' +
                "\"version\":{" +
                "\"name\":\"" + response.name() + "\"," + "\"protocol\":" + response.protocol()
                + "}," +
                "\"players\":{" +
                "\"max\":" + response.max() + ",\"online\":" + response.online()
                + ",\"sample\":[]}," +
                "\"description\":" + Buffer.getGsonSerializer(this.connection.protocolVersion())
                .serialize(response.description()) +
                '}'));
          } else {
            this.connection.close();
          }
        }, this.connection.executor());
    return true;
  }

  @Override
  public boolean handle(StatusPingPacket statusPing) {
    this.connection.sendAndClose(statusPing);
    return true;
  }
}
