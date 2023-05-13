package de.bauhd.minecraft.server.protocol.packet.play.block;

import de.bauhd.minecraft.server.world.Position;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class BlockUpdate implements Packet {

    private final double x;
    private final double y;
    private final double z;
    private final int blockId;

    public BlockUpdate(final Position position, final int blockId) {
        this(position.x(), position.y(), position.z(), blockId);
    }

    public BlockUpdate(final double x, final double y, final double z, final int blockId) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockId = blockId;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writePosition(this.x, this.y, this.z)
                .writeVarInt(this.blockId);
    }

    @Override
    public String toString() {
        return "BlockUpdate{" +
                "x=" + this.x +
                ", y=" + this.y +
                ", z=" + this.z +
                ", blockId=" + this.blockId +
                '}';
    }
}
