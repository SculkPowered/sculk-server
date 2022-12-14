package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class SimulationDistance implements Packet {

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeVarInt(10);
    }
}
