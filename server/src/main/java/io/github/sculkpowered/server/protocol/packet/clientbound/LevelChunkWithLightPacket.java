package io.github.sculkpowered.server.protocol.packet.clientbound;

import static io.github.sculkpowered.server.util.CoordinateUtil.chunkPositionXFromBlockIndex;
import static io.github.sculkpowered.server.util.CoordinateUtil.chunkPositionYFromBlockIndex;
import static io.github.sculkpowered.server.util.CoordinateUtil.chunkPositionZFromBlockIndex;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.world.block.Block;
import io.github.sculkpowered.server.world.block.Block.Entity;
import io.github.sculkpowered.server.world.chunk.LightData;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.kyori.adventure.nbt.CompoundBinaryTag;

public final class LevelChunkWithLightPacket implements ClientboundPacket {

  private final int chunkX;
  private final int chunkZ;
  private final CompoundBinaryTag heightmaps;
  private final byte[] data;
  private final LightData lightData;
  private final Int2ObjectMap<Entity<?>> blockEntities;

  public LevelChunkWithLightPacket(final int chunkX,
      final int chunkZ,
      final CompoundBinaryTag heightmaps,
      final byte[] data,
      final LightData lightData,
      final Int2ObjectMap<Block.Entity<?>> blockEntities) {
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
        .writeBinaryTag(this.heightmaps)
        .writeByteArray(this.data)
        .writeVarInt(this.blockEntities.size());
    for (final var entry : this.blockEntities.int2ObjectEntrySet()) {
      final var point = entry.getIntKey();
      final var block = entry.getValue();
      buf
          .writeByte((byte) (((chunkPositionXFromBlockIndex(point) & 15) << 4) | (
              (chunkPositionZFromBlockIndex(point) & 0xF) & 15)))
          .writeShort((short) chunkPositionYFromBlockIndex(point))
          .writeVarInt(block.getEntityId())
          .writeBinaryTag(block.nbt());
    }
    this.lightData.write(buf);
  }

  @Override
  public String toString() {
    return "LevelChunkWithLightPacket{" +
        "chunkX=" + this.chunkX +
        ", chunkZ=" + this.chunkZ +
        ", data=byte[" + this.data.length + "]" +
        ", lightData=" + this.lightData +
        '}';
  }
}
