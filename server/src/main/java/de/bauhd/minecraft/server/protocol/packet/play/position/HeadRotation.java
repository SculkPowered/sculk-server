package de.bauhd.minecraft.server.protocol.packet.play.position;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class HeadRotation implements Packet {

    private final int entityId;
    private final float yaw;

    public HeadRotation(final int entityId, final float yaw) {
        this.entityId = entityId;
        this.yaw = yaw;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.entityId);
        buf.writeByte((byte) (this.yaw * 256 / 360));
    }
}
