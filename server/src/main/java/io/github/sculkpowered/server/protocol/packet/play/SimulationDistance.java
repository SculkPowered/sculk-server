package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

public final class SimulationDistance implements Packet {

  private final int simulationDistance;

  public SimulationDistance(final int simulationDistance) {
    this.simulationDistance = simulationDistance;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.simulationDistance);
  }
}
