package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

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
                .writeByte((byte) 0x00);
        writeVarInt(buf, 1);
        buf.writeBoolean(false);
    }
}
