package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readVarInt;

public final class ConfirmTeleportation implements Packet {

    private int teleportId;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.teleportId = readVarInt(buf);
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public String toString() {
        return "ConfirmTeleportation{" +
                "teleportId=" + this.teleportId +
                '}';
    }
}
