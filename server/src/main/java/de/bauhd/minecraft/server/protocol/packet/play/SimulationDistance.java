package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

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
