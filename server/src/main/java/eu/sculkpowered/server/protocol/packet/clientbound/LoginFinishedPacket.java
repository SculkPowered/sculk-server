package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.entity.player.GameProfile;
import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class LoginFinishedPacket implements ClientboundPacket {

  private final GameProfile profile;

  public LoginFinishedPacket(final GameProfile profile) {
    this.profile = profile;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeUniqueId(this.profile.uniqueId())
        .writeString(this.profile.name())
        .writeProfileProperties(this.profile.properties());
  }

  @Override
  public String toString() {
    return "GameProfilePacket{" +
        "profile=" + this.profile +
        '}';
  }
}
