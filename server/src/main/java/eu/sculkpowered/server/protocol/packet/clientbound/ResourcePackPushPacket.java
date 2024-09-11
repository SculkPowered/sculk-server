package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import java.util.UUID;
import net.kyori.adventure.text.Component;

public final class ResourcePackPushPacket implements ClientboundPacket {

  private final UUID uniqueId;
  private final String url;
  private final String hash;
  private final boolean forced;
  private final Component prompt;

  public ResourcePackPushPacket(
      final UUID uniqueId,
      final String url,
      final String hash,
      final boolean forced,
      final Component prompt
  ) {
    this.uniqueId = uniqueId;
    this.url = url;
    this.hash = hash;
    this.forced = forced;
    this.prompt = prompt;
  }

  @Override
  public void encode(Buffer buf) {

  }

  @Override
  public String toString() {
    return "AddResourcePack{" +
        "uniqueId=" + this.uniqueId +
        ", url='" + this.url + '\'' +
        ", hash='" + this.hash + '\'' +
        ", forced=" + this.forced +
        ", prompt=" + this.prompt +
        '}';
  }
}
