package de.bauhd.minecraft.server.protocol.packet.play.block;

import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class BlockUpdate implements Packet {

    private final Position position;
    private final int blockId;

    public BlockUpdate(final Position position, final int blockId) {
        this.position = position;
        this.blockId = blockId;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writePosition(this.position)
                .writeVarInt(this.blockId);
    }

    @Override
    public String toString() {
        return "BlockUpdate{" +
                "position=" + this.position +
                ", blockId=" + this.blockId +
                '}';
    }
}
