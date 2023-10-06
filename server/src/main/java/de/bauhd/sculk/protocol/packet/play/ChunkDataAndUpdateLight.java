package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.world.Point;
import de.bauhd.sculk.world.block.Block;
import de.bauhd.sculk.world.chunk.LightData;
import net.kyori.adventure.nbt.CompoundBinaryTag;

import java.util.Map;

public final class ChunkDataAndUpdateLight implements Packet {

    private final int chunkX;
    private final int chunkZ;
    private final CompoundBinaryTag heightmaps;
    private final byte[] data;
    private final LightData lightData;
    private final Map<Point, Block.Entity<?>> blockEntities;

    public ChunkDataAndUpdateLight(final int chunkX,
                                   final int chunkZ,
                                   final CompoundBinaryTag heightmaps,
                                   final byte[] data,
                                   final LightData lightData,
                                   final Map<Point, Block.Entity<?>> blockEntities) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.heightmaps = heightmaps;
        this.data = data;
        this.lightData = lightData;
        this.blockEntities = blockEntities;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeInt(this.chunkX)
                .writeInt(this.chunkZ)
                .writeCompoundTag(this.heightmaps)
                .writeByteArray(this.data)
                .writeVarInt(this.blockEntities.size());
        for (final var entry : this.blockEntities.entrySet()) {
            final var point = entry.getKey();
            final var block = entry.getValue();
            buf
                    .writeByte((byte) (((point.x() & 15) << 4) | (point.z() & 15)))
                    .writeShort((short) point.y())
                    .writeVarInt(block.getEntityId())
                    .writeCompoundTag(block.nbt());
        }
        this.lightData.write(buf);
    }

    @Override
    public String toString() {
        return "ChunkDataAndUpdateLight{" +
                "chunkX=" + this.chunkX +
                ", chunkZ=" + this.chunkZ +
                ", heightmaps=" + this.heightmaps +
                ", data=byte[" + this.data.length + "]" +
                ", lightData=" + this.lightData +
                '}';
    }
}
