package io.github.sculkpowered.server.protocol.packet.handler;

import io.github.sculkpowered.server.protocol.Protocol;
import io.github.sculkpowered.server.protocol.SculkConnection;
import io.github.sculkpowered.server.protocol.State;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.handshake.Handshake;
import io.github.sculkpowered.server.protocol.packet.login.Disconnect;
import net.kyori.adventure.text.Component;

public final class HandshakePacketHandler extends PacketHandler {

  private final SculkConnection connection;

  public HandshakePacketHandler(final SculkConnection connection) {
    this.connection = connection;
  }

  @Override
  public boolean handle(Handshake handshake) {
    this.connection.setState(handshake.nextStatus());
    if (handshake.nextStatus() == State.LOGIN
        && (handshake.version() != Protocol.VERSION_PROTOCOL)) {
      this.connection.send(new Disconnect(Component
          .translatable("multiplayer.disconnect.outdated_client",
              Component.text(Protocol.VERSION_NAME))));
      this.connection.close();
      return true;
    }
    this.connection.setVersion(handshake.version());
    this.connection.setServerAddress(handshake.serverAddress());
    return true;
  }
}
