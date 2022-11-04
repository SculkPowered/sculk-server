package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.nbt.CompoundBinaryTag;

import java.util.BitSet;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.*;

public final class ChunkDataAndUpdateLight implements Packet {

    private int chunkX;
    private int chunkZ;
    private CompoundBinaryTag heightmaps;
    private byte[] data;
    private boolean trustEdges;
    private BitSet skyLightMask;
    private BitSet blockLightMask;
    private BitSet emptySkyLightMask;
    private BitSet emptyBlockLightMask;
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

    public ChunkDataAndUpdateLight() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeInt(this.chunkX);
        buf.writeInt(this.chunkZ);
        writeCompoundTag(buf, this.heightmaps);
        writeVarInt(buf, this.data.length);
        buf.writeBytes(this.data);
        writeVarInt(buf, 0); // Block entities
        buf.writeBoolean(this.trustEdges);
        writeBitSet(buf, this.skyLightMask); // Sky Light Mask
        writeBitSet(buf, this.blockLightMask); // Block Light Mask
        writeBitSet(buf, this.emptySkyLightMask); // Empty Sky Light Mask
        writeBitSet(buf, this.emptyBlockLightMask); // Empty Block Light Mask
        writeVarInt(buf, 0); // Sky Light Array
        writeVarInt(buf, 0); // Block Light Array*/ // for 1.19
    }

}
