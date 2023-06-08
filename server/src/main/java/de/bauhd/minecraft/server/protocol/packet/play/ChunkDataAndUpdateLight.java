package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.world.chunk.LightData;
import net.kyori.adventure.nbt.CompoundBinaryTag;

public final class ChunkDataAndUpdateLight implements Packet {

    private final int chunkX;
    private final int chunkZ;
    private final CompoundBinaryTag heightmaps;
    private final byte[] data;
    private final LightData lightData;

    public ChunkDataAndUpdateLight(final int chunkX,
                                   final int chunkZ,
                                   final CompoundBinaryTag heightmaps,
                                   final byte[] data,
                                   final LightData lightData) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.heightmaps = heightmaps;
        this.data = data;
        this.lightData = lightData;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeInt(this.chunkX)
                .writeInt(this.chunkZ)
                .writeCompoundTag(this.heightmaps)
                .writeByteArray(this.data)
                .writeVarInt(0); // Block entities
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
