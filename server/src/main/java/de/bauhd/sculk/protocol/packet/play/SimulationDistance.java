package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

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
