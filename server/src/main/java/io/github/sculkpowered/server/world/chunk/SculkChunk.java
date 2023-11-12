package io.github.sculkpowered.server.world.chunk;

import static io.github.sculkpowered.server.util.CoordinateUtil.blockIndex;
import static io.github.sculkpowered.server.util.CoordinateUtil.chunkCoordinate;
import static io.github.sculkpowered.server.util.CoordinateUtil.relativeCoordinate;

import io.github.sculkpowered.server.entity.AbstractEntity;
import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.play.chunk.ChunkDataAndUpdateLight;
import io.github.sculkpowered.server.protocol.packet.play.block.BlockEntityData;
import io.github.sculkpowered.server.protocol.packet.play.block.BlockUpdate;
import io.github.sculkpowered.server.world.SculkWorld;
import io.github.sculkpowered.server.world.biome.Biome;
import io.github.sculkpowered.server.world.block.Block;
import io.github.sculkpowered.server.world.block.Block.Entity;
import io.github.sculkpowered.server.world.block.BlockState;
import io.github.sculkpowered.server.world.dimension.Dimension;
import io.github.sculkpowered.server.world.section.Section;
import io.netty.buffer.ByteBufAllocator;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

public final class SculkChunk implements Chunk {

  private final Dimension dimension;
  private final int x;
  private final int z;
  private final Section[] sections;
  private final CompoundBinaryTag heightmaps;
  private final Set<SculkPlayer> viewers = new HashSet<>();
  private final Set<AbstractEntity> entities = new HashSet<>();
  private final Int2ObjectMap<Block.Entity<?>> blockEntities = new Int2ObjectOpenHashMap<>();

  private ChunkDataAndUpdateLight packet;

  public SculkChunk(final SculkWorld world, final int chunkX, final int chunkZ) {
    this(world, chunkX, chunkZ, newSections(world.dimension()),
        world.dimension().heightmaps());
  }

  public SculkChunk(final SculkWorld world, final int chunkX, final int chunkZ,
      final Section[] sections,
      final CompoundBinaryTag heightmaps) {
    this.dimension = world.dimension();
    this.x = chunkX;
    this.z = chunkZ;
    this.sections = sections;
    this.heightmaps = heightmaps;
  }

  @Override
  public int x() {
    return this.x;
  }

  @Override
  public int z() {
    return this.z;
  }

  @Override
  public void block(int x, int y, int z, @NotNull BlockState block) {
    final var id = block.id();
    this.section(y).blocks()
        .set(relativeCoordinate(x), relativeCoordinate(y), relativeCoordinate(z), id);
    this.packet = null;
    final var blockIndex = blockIndex(x, y, z);
    if (block instanceof Block.Entity<?> entity) {
      this.blockEntities.put(blockIndex, entity);
    } else {
      this.blockEntities.remove(blockIndex);
    }
    if (!this.viewers.isEmpty()) {
      final var packet = new BlockUpdate(x, y, z, id);
      if (block instanceof Block.Entity<?> entity) {
        final var entityData = new BlockEntityData(x, y, z, entity.getEntityId(), entity.nbt());
        for (final var viewer : this.viewers) {
          viewer.send(packet);
          viewer.send(entityData);
        }
      } else {
        for (final var viewer : this.viewers) {
          viewer.send(packet);
        }
      }
    }
  }

  @Override
  public @NotNull BlockState block(int x, int y, int z) {
    return Block.get(this.section(y).blocks()
        .get(relativeCoordinate(x), relativeCoordinate(y), relativeCoordinate(z)));
  }

  @Override
  public void biome(int x, int y, int z, @NotNull Biome biome) {
    this.section(y).biomes()
        .set(relativeCoordinate(x) / 4, relativeCoordinate(y) / 4, relativeCoordinate(z) / 4,
            biome.id());
    this.packet = null;
  }

  public void send(SculkPlayer player) {
    if (this.packet == null) {
      final var buf = new Buffer(
          ByteBufAllocator.DEFAULT.buffer(this.sections.length * 8)); // minimum amount
      final var skyMask = new BitSet();
      final var blockMask = new BitSet();
      final var emptySkyMask = new BitSet();
      final var emptyBlockMask = new BitSet();
      final var skyLight = new ArrayList<byte[]>();
      final var blockLight = new ArrayList<byte[]>();
      var index = 0;
      for (final var section : this.sections) {
        index++;

        buf.writeShort(section.blocks().size());
        section.blocks().write(buf);
        section.biomes().write(buf);

        if (section.skyLight().length != 0) {
          skyLight.add(section.skyLight());
          skyMask.set(index);
        } else {
          emptySkyMask.set(index);
        }
        if (section.blockLight().length != 0) {
          blockLight.add(section.blockLight());
          blockMask.set(index);
        } else {
          emptyBlockMask.set(index);
        }
      }
      this.packet = new ChunkDataAndUpdateLight(
          this.x,
          this.z,
          this.heightmaps,
          buf.readAll(),
          new LightData(skyMask, blockMask, emptySkyMask, emptyBlockMask,
              skyLight.toArray(new byte[][]{}), blockLight.toArray(new byte[][]{})),
          this.blockEntities
      );
      buf.close();
    }
    player.send(this.packet);
  }

  public Section section(final int y) {
    return this.sections[chunkCoordinate(y) - this.dimension.minimumSections()];
  }

  public Section[] sections() {
    return this.sections;
  }

  public CompoundBinaryTag heightmaps() {
    return this.heightmaps;
  }

  public Int2ObjectMap<Entity<?>> blockEntries() {
    return this.blockEntities;
  }

  @Override
  public String toString() {
    return "SculkChunk{" +
        "dimension=" + this.dimension.name() +
        ", x=" + this.x +
        ", z=" + this.z +
        '}';
  }

  public Set<SculkPlayer> viewers() {
    return this.viewers;
  }

  public Set<AbstractEntity> entities() {
    return this.entities;
  }

  public void tick() {
    for (final var entity : this.entities) {
      entity.tick();
    }
  }

  private static Section[] newSections(Dimension dimension) {
    final var capacity = dimension.maximumSections() - dimension.minimumSections();
    final var sections = new Section[capacity];
    for (var i = 0; i < capacity; i++) {
      sections[i] = new Section();
    }
    return sections;
  }
}
