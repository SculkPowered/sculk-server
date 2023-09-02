package de.bauhd.sculk.protocol.packet.play.block;

import de.bauhd.sculk.world.Position;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import net.kyori.adventure.nbt.CompoundBinaryTag;

public final class BlockEntityData implements Packet {

    private final Position position;
    private final int type;
    private final CompoundBinaryTag nbt;

    public BlockEntityData(final Position position, final int type, final CompoundBinaryTag nbt) {
        this.position = position;
        this.type = type;
        this.nbt = nbt;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writePosition(this.position)
                .writeVarInt(this.type)
                .writeCompoundTag(this.nbt);
    }

    @Override
    public String toString() {
        return "BlockEntityData{" +
                "position=" + this.position +
                ", type=" + this.type +
                ", nbt=" + this.nbt +
                '}';
    }
}
