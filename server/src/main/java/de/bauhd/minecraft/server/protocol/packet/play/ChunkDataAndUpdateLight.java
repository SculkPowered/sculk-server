package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.nbt.CompoundBinaryTag;

import java.util.Arrays;
import java.util.BitSet;

public final class ChunkDataAndUpdateLight implements Packet {

    private final int chunkX;
    private final int chunkZ;
    private final CompoundBinaryTag heightmaps;
    private final byte[] data;
    private final boolean trustEdges;
    private final BitSet skyLightMask;
    private final BitSet blockLightMask;
    private final BitSet emptySkyLightMask;
    private final BitSet emptyBlockLightMask;
    // TODO sky light block light

    public ChunkDataAndUpdateLight(final int chunkX,
                                   final int chunkZ,
                                   final CompoundBinaryTag heightmaps,
                                   final byte[] data,
                                   final boolean trustEdges,
                                   final BitSet skyLightMask,
                                   final BitSet blockLightMask,
                                   final BitSet emptySkyLightMask,
                                   final BitSet emptyBlockLightMask) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.heightmaps = heightmaps;
        this.data = data;
        this.trustEdges = trustEdges;
        this.skyLightMask = skyLightMask;
        this.blockLightMask = blockLightMask;
        this.emptySkyLightMask = emptySkyLightMask;
        this.emptyBlockLightMask = emptyBlockLightMask;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeInt(this.chunkX)
                .writeInt(this.chunkZ)
                .writeCompoundTag(this.heightmaps)
                .writeByteArray(this.data)
                .writeVarInt(0) // Block entities
                .writeBoolean(this.trustEdges)
                .writeBitSet(this.skyLightMask) // Skylight Mask
                .writeBitSet(this.blockLightMask) // Block Light Mask
                .writeBitSet(this.emptySkyLightMask) // Empty Skylight Mask
                .writeBitSet(this.emptyBlockLightMask) // Empty Block Light Mask
                .writeVarInt(0) // Skylight Array
                .writeVarInt(0); // Block Light Array // for 1.19
    }

    @Override
    public String toString() {
        return "ChunkDataAndUpdateLight{" +
                "chunkX=" + this.chunkX +
                ", chunkZ=" + this.chunkZ +
                ", heightmaps=" + this.heightmaps +
                ", data=" + Arrays.toString(this.data) +
                ", trustEdges=" + this.trustEdges +
                ", skyLightMask=" + this.skyLightMask +
                ", blockLightMask=" + this.blockLightMask +
                ", emptySkyLightMask=" + this.emptySkyLightMask +
                ", emptyBlockLightMask=" + this.emptyBlockLightMask +
                '}';
    }
}
