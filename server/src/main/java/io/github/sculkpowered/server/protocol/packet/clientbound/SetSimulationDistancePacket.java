package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;

public final class SetSimulationDistancePacket implements ClientboundPacket {

  private final int simulationDistance;

  public SetSimulationDistancePacket(final int simulationDistance) {
    this.simulationDistance = simulationDistance;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.simulationDistance);
  }

  @Override
  public String toString() {
    return "SetSimulationDistancePacket{" +
        "simulationDistance=" + this.simulationDistance +
        '}';
  }
}
