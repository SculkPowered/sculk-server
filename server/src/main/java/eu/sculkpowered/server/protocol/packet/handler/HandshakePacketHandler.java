package eu.sculkpowered.server.protocol.packet.handler;

import eu.sculkpowered.server.protocol.Protocol;
import eu.sculkpowered.server.protocol.SculkConnection;
import eu.sculkpowered.server.protocol.State;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.serverbound.IntentionPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.LoginDisconnectPacket;
import net.kyori.adventure.text.Component;

public final class HandshakePacketHandler extends PacketHandler {

  private final SculkConnection connection;

  public HandshakePacketHandler(final SculkConnection connection) {
    this.connection = connection;
  }

  @Override
  public boolean handle(IntentionPacket intention) {
    State state;
    if (intention.intent() == 1) {
      state = State.STATUS;
    } else if (intention.intent() == 2) {
      state = State.LOGIN;
    } else {
      return true; // Transfer or something weird
    }

    this.connection.setState(state);
    if (state == State.LOGIN
        && (intention.version() != Protocol.VERSION_PROTOCOL)) {
      this.connection.send(new LoginDisconnectPacket(Component
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
