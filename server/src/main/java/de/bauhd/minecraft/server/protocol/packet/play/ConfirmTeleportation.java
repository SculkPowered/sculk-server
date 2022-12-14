package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class ConfirmTeleportation implements Packet {

    private int teleportId;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.teleportId = buf.readVarInt();
    }

    @Override
    public String toString() {
        return "ConfirmTeleportation{" +
                "teleportId=" + this.teleportId +
                '}';
    }
}
