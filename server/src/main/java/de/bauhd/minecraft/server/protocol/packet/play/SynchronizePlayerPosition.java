package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class SynchronizePlayerPosition implements Packet {

    private final Position position;

    public SynchronizePlayerPosition(final Position position) {
        this.position = position;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeDouble(this.position.x())
                .writeDouble(this.position.y())
                .writeDouble(this.position.z())
                .writeFloat(this.position.yaw())
                .writeFloat(this.position.pitch())
                .writeByte((byte) 0x00)
                .writeVarInt(1)
                .writeBoolean(false);
    }

    @Override
    public String toString() {
        return "SynchronizePlayerPosition{" +
                "position=" + this.position +
                '}';
    }
}
