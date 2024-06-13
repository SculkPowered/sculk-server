package io.github.sculkpowered.server.protocol.packet.handler;

import io.github.sculkpowered.server.protocol.Protocol;
import io.github.sculkpowered.server.protocol.SculkConnection;
import io.github.sculkpowered.server.protocol.State;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.serverbound.Intention;
import io.github.sculkpowered.server.protocol.packet.clientbound.LoginDisconnect;
import net.kyori.adventure.text.Component;

public final class HandshakePacketHandler extends PacketHandler {

  private final SculkConnection connection;

  public HandshakePacketHandler(final SculkConnection connection) {
    this.connection = connection;
  }

  @Override
  public boolean handle(Intention intention) {
    this.connection.setState(intention.nextStatus());
    if (intention.nextStatus() == State.LOGIN
        && (intention.version() != Protocol.VERSION_PROTOCOL)) {
      this.connection.send(new LoginDisconnect(Component
          .translatable("multiplayer.disconnect.outdated_client",
              Component.text(Protocol.VERSION_NAME))));
      this.connection.close();
      return true;
    }
    this.connection.setVersion(intention.version());
    this.connection.setServerAddress(intention.serverAddress());
    return true;
  }
}
