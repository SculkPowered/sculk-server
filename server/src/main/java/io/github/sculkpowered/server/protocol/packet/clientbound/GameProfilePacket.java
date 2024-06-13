package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.entity.player.GameProfile;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class GameProfilePacket implements ClientboundPacket {

  private final GameProfile profile;

  public GameProfilePacket(final GameProfile profile) {
    this.profile = profile;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeUniqueId(this.profile.uniqueId())
        .writeString(this.profile.name())
        .writeProfileProperties(this.profile.properties())
        .writeBoolean(true);
  }

  @Override
  public String toString() {
    return "GameProfilePacket{" +
        "profile=" + this.profile +
        '}';
  }
}
