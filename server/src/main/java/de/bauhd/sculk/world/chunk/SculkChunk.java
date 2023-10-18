package de.bauhd.sculk.world.chunk;

import static de.bauhd.sculk.util.CoordinateUtil.chunkCoordinate;
import static de.bauhd.sculk.util.CoordinateUtil.relativeCoordinate;

import de.bauhd.sculk.entity.AbstractEntity;
import de.bauhd.sculk.entity.player.SculkPlayer;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.play.chunk.ChunkDataAndUpdateLight;
import de.bauhd.sculk.protocol.packet.play.block.BlockEntityData;
import de.bauhd.sculk.protocol.packet.play.block.BlockUpdate;
import de.bauhd.sculk.world.Point;
import de.bauhd.sculk.world.SculkWorld;
import de.bauhd.sculk.world.biome.Biome;
import de.bauhd.sculk.world.block.Block;
import de.bauhd.sculk.world.block.BlockState;
import de.bauhd.sculk.world.dimension.Dimension;
import de.bauhd.sculk.world.section.Section;
import io.netty.buffer.ByteBufAllocator;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
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
  private final Map<Point, Block.Entity<?>> blockEntities = new ConcurrentHashMap<>();

  private ChunkDataAndUpdateLight packet;

  public SculkChunk(final SculkWorld world, final int chunkX, final int chunkZ) {
    this(world, chunkX, chunkZ, newSections(world.getDimension()),
        world.getDimension().heightmaps());
  }

  public SculkChunk(final SculkWorld world, final int chunkX, final int chunkZ,
      final Section[] sections,
      final CompoundBinaryTag heightmaps) {
    this.dimension = world.getDimension();
    this.x = chunkX;
    this.z = chunkZ;
    this.sections = sections;
    this.heightmaps = heightmaps;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getZ() {
    return this.z;
  }

  @Override
  public void setBlock(int x, int y, int z, @NotNull BlockState block) {
    final var id = block.getId();
    this.section(y).blocks()
        .set(relativeCoordinate(x), relativeCoordinate(y), relativeCoordinate(z), id);
    this.packet = null;
    final var point = new Point(x, y, z);
    if (block instanceof Block.Entity<?> entity) {
      this.blockEntities.put(point, entity);
    } else {
      this.blockEntities.remove(point);
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
  public @NotNull BlockState getBlock(int x, int y, int z) {
    return Block.get(this.section(y).blocks()
        .get(relativeCoordinate(x), relativeCoordinate(y), relativeCoordinate(z)));
  }

  @Override
  public void setBiome(int x, int y, int z, @NotNull Biome biome) {
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

  public Map<Point, Block.Entity<?>> blockEntities() {
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
