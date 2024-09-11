package eu.sculkpowered.server.world.chunk;

import eu.sculkpowered.server.protocol.Buffer;
import java.util.BitSet;

public class LightData {

  private final BitSet skyMask;
  private final BitSet blockMask;
  private final BitSet emptySkyMask;
  private final BitSet emptyBlockMask;
  private final byte[][] skyLight;
  private final byte[][] blockLight;

  public LightData(final BitSet skyMask, final BitSet blockMask, final BitSet emptySkyMask,
      final BitSet emptyBlockMask,
      final byte[][] skyLight, final byte[][] blockLight) {
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
    buf.writeVarInt(this.skyLight.length);
    for (final var bytes : this.skyLight) {
      buf.writeByteArray(bytes);
    }
    buf.writeVarInt(this.blockLight.length);
    for (final var bytes : this.blockLight) {
      buf.writeByteArray(bytes);
    }
  }
}
