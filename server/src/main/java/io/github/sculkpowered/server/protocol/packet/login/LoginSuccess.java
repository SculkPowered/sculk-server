package io.github.sculkpowered.server.protocol.packet.login;

import io.github.sculkpowered.server.entity.player.GameProfile;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class LoginSuccess implements Packet {

  private final GameProfile profile;

  public LoginSuccess(GameProfile profile) {
    this.profile = profile;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeUniqueId(this.profile.uniqueId())
        .writeString(this.profile.name())
        .writeVarInt(this.profile.properties().size());
    for (final var property : this.profile.properties()) {
      buf.writeString(property.key()).writeString(property.value());
      if (property.signature() != null) {
        buf.writeBoolean(true);
        buf.writeString(property.signature());
      } else {
        buf.writeBoolean(false);
      }
    }
  }

  @Override
  public String toString() {
    return "LoginSuccess{" +
        "profile=" + this.profile +
        '}';
  }
}
