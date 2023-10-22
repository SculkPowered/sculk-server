package io.github.sculkpowered.server.protocol.packet.config;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

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
