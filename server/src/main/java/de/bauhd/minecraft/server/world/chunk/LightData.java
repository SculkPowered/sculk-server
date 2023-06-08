package de.bauhd.minecraft.server.world.chunk;

import de.bauhd.minecraft.server.protocol.Buffer;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class LightData {

    private final BitSet skyMask;
    private final BitSet blockMask;
    private final BitSet emptySkyMask;
    private final BitSet emptyBlockMask;
    private final List<byte[]> skyLight;
    private final List<byte[]> blockLight;

    public LightData(final BitSet skyMask, final BitSet blockMask, final BitSet emptySkyMask, final BitSet emptyBlockMask,
                     final List<byte[]> skyLight, final List<byte[]> blockLight) {
        this.skyMask = skyMask;
        this.blockMask = blockMask;
        this.emptySkyMask = emptySkyMask;
        this.emptyBlockMask = emptyBlockMask;
        this.skyLight = skyLight;
        this.blockLight = blockLight;
    }

    public void write(Buffer buf) {
        buf
                .writeBitSet(this.skyMask)
                .writeBitSet(this.blockMask)
                .writeBitSet(this.emptySkyMask)
                .writeBitSet(this.emptyBlockMask);
        buf.writeVarInt(this.skyLight.size());
        for (final var bytes : this.skyLight) {
            buf.writeByteArray(bytes);
        }
        buf.writeVarInt(this.blockLight.size());
        for (final var bytes : this.blockLight) {
            buf.writeByteArray(bytes);
        }
    }
}
