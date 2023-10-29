package io.github.sculkpowered.server.protocol.packet.handler;

import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.event.connection.ServerPingEvent;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.Protocol;
import io.github.sculkpowered.server.protocol.SculkConnection;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.status.StatusPing;
import io.github.sculkpowered.server.protocol.packet.status.StatusRequest;
import io.github.sculkpowered.server.protocol.packet.status.StatusResponse;
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
    this.server.getEventHandler().call(new ServerPingEvent(this.connection))
        .thenAcceptAsync(event -> {
          final var response = event.getResponse();
          if (response != null) {
            if (response.protocol() == Integer.MIN_VALUE) {
              response.protocol(Protocol.VERSION_PROTOCOL);
            }
            if (response.online() == Integer.MIN_VALUE) {
              response.online(this.server.getPlayerCount());
            }
            if (response.description() == null) {
              response.description(Component.empty());
            }
            this.connection.send(new StatusResponse('{' +
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
  public boolean handle(StatusPing statusPing) {
    this.connection.sendAndClose(statusPing);
    return true;
  }
}
