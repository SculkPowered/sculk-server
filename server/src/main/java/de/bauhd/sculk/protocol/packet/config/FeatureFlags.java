package de.bauhd.sculk.protocol.packet.config;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class FeatureFlags implements Packet {

  private final String[] features;

  public FeatureFlags(final String[] features) {
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
