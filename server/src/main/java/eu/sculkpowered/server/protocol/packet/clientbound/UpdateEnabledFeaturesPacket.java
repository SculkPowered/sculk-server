package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class UpdateEnabledFeaturesPacket implements ClientboundPacket {

  private final String[] features;

  public UpdateEnabledFeaturesPacket(final String[] features) {
    this.features = features;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.features.length);
    for (final var feature : this.features) {
      buf.writeString(feature);
    }
  }
}
