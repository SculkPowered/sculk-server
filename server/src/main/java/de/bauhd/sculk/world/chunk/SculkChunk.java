package de.bauhd.sculk.world.chunk;

import de.bauhd.sculk.entity.AbstractEntity;
import de.bauhd.sculk.entity.player.SculkPlayer;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.play.ChunkDataAndUpdateLight;
import de.bauhd.sculk.protocol.packet.play.block.BlockUpdate;
import de.bauhd.sculk.world.SculkWorld;
import de.bauhd.sculk.world.biome.Biome;
import de.bauhd.sculk.world.block.Block;
import de.bauhd.sculk.world.block.BlockState;
import de.bauhd.sculk.world.dimension.Dimension;
import de.bauhd.sculk.world.section.Section;
import de.bauhd.sculk.util.CoordinateUtil;
import io.netty.buffer.ByteBufAllocator;
import it.unimi.dsi.fastutil.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public final class SculkChunk implements Chunk {

    private final SculkWorld world;
    private final Dimension dimension;
    private final int x;
    private final int z;
    private final Section[] sections;
    private final List<SculkPlayer> viewers = new ArrayList<>();
    private final List<AbstractEntity> entities = new ArrayList<>();

    private ChunkDataAndUpdateLight packet;

    public SculkChunk(final SculkWorld world, final int chunkX, final int chunkZ) {
        this.world = world;
        this.dimension = this.world.getDimension();
        this.x = chunkX;
        this.z = chunkZ;
        final var capacity = this.dimension.maximumSections() - this.dimension.minimumSections();
        this.sections = new Section[capacity];
        for (var i = 0; i < capacity; i++) {
            this.sections[i] = new Section();
        }
    }

    public SculkChunk(final SculkWorld world, final int chunkX, final int chunkZ, final Section[] sections) {
        this.world = world;
        this.dimension = this.world.getDimension();
        this.x = chunkX;
        this.z = chunkZ;
        this.sections = sections;
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
        this.section(y).blocks().set(CoordinateUtil.relativeCoordinate(x), CoordinateUtil.relativeCoordinate(y), CoordinateUtil.relativeCoordinate(z), id);
        this.packet = null;
        if (!this.viewers.isEmpty()) {
            final var packet = new BlockUpdate(x, y, z, id);
            for (final var viewer : this.viewers) {
                viewer.send(packet);
            }
        }
    }

    @Override
    public @NotNull BlockState getBlock(int x, int y, int z) {
        return Block.get(this.section(y).blocks().get(CoordinateUtil.relativeCoordinate(x), CoordinateUtil.relativeCoordinate(y), CoordinateUtil.relativeCoordinate(z)));
    }

    @Override
    public void setBiome(int x, int y, int z, @NotNull Biome biome) {
        this.section(y).biomes().set(CoordinateUtil.relativeCoordinate(x) / 4, CoordinateUtil.relativeCoordinate(y) / 4, CoordinateUtil.relativeCoordinate(z) / 4,
                biome.id());
        this.packet = null;
    }

    public void send(SculkPlayer player) {
        if (this.packet == null) {
            final var data = this.sectionsToData();
            this.packet = new ChunkDataAndUpdateLight(
                    this.x,
                    this.z,
                    this.world.getDimension().heightmaps(),
                    data.left(),
                    data.right()
            );
        }
        player.send(this.packet);
    }

    public Section section(final int y) {
        return this.sections[CoordinateUtil.chunkCoordinate(y) - this.dimension.minimumSections()];
    }

    private Pair<byte[], LightData> sectionsToData() {
        final var buf = new Buffer(ByteBufAllocator.DEFAULT.buffer(this.sections.length * 8)); // minimum amount
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
        final var data = buf.readAll();
        buf.close();
        return Pair.of(data, new LightData(skyMask, blockMask, emptySkyMask, emptyBlockMask,
                skyLight.toArray(new byte[][]{}), blockLight.toArray(new byte[][]{})));
    }

    @Override
    public String toString() {
        return "SculkChunk{" +
                "dimension=" + this.dimension.name() +
                ", x=" + this.x +
                ", z=" + this.z +
                '}';
    }

    public List<SculkPlayer> viewers() {
        return this.viewers;
    }

    public List<AbstractEntity> entities() {
        return this.entities;
    }

    public void tick() {
        for (final var entity : this.entities) {
            entity.tick();
        }
    }
}
