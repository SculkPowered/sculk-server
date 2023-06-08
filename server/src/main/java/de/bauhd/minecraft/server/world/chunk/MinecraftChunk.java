package de.bauhd.minecraft.server.world.chunk;

import de.bauhd.minecraft.server.entity.AbstractEntity;
import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.play.ChunkDataAndUpdateLight;
import de.bauhd.minecraft.server.protocol.packet.play.block.BlockUpdate;
import de.bauhd.minecraft.server.world.MinecraftWorld;
import de.bauhd.minecraft.server.world.biome.Biome;
import de.bauhd.minecraft.server.world.block.Block;
import de.bauhd.minecraft.server.world.dimension.Dimension;
import de.bauhd.minecraft.server.world.section.Section;
import io.netty5.buffer.DefaultBufferAllocators;
import it.unimi.dsi.fastutil.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class MinecraftChunk implements Chunk {

    private final MinecraftWorld world;
    private final Dimension dimension;
    private final int x;
    private final int z;
    private final Section[] sections;
    private final List<MinecraftPlayer> viewers = new ArrayList<>();
    private final List<AbstractEntity> entities = new ArrayList<>();

    private ChunkDataAndUpdateLight packet;

    public MinecraftChunk(final MinecraftWorld world, final int chunkX, final int chunkZ) {
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

    public MinecraftChunk(final MinecraftWorld world, final int chunkX, final int chunkZ, final Section[] sections) {
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
    public void setBlock(int x, int y, int z, @NotNull Block block) {
        final var id = block.stateId();
        this.section(y).blocks().set(this.relativeCoordinate(x), this.relativeCoordinate(y), this.relativeCoordinate(z), id);
        this.packet = null;
        if (this.viewers.size() != 0) {
            final var packet = new BlockUpdate(x, y, z, id);
            for (final var viewer : this.viewers) {
                viewer.send(packet);
            }
        }
    }

    @Override
    public void setBiome(int x, int y, int z, @NotNull Biome biome) {
        this.section(y).biomes().set(this.relativeCoordinate(x) / 4, this.relativeCoordinate(y) / 4, this.relativeCoordinate(z) / 4,
                biome.id());
        this.packet = null;
    }

    public void send(MinecraftPlayer player) {
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
        return this.sections[this.world.chunkCoordinate(y) - this.dimension.minimumSections()];
    }

    private Pair<byte[], LightData> sectionsToData() {
        final var buf = new Buffer(DefaultBufferAllocators.offHeapAllocator().allocate(this.sections.length * 8)); // minimum amount
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
        return Pair.of(data, new LightData(skyMask, blockMask, emptySkyMask, emptyBlockMask, skyLight, blockLight));
    }

    private int relativeCoordinate(final int coordinate) {
        return coordinate & 0xF;
    }

    @Override
    public String toString() {
        return "MinecraftChunk{" +
                "dimension=" + this.dimension.nbt().getString("name") +
                ", x=" + this.x +
                ", z=" + this.z +
                '}';
    }

    public List<MinecraftPlayer> viewers() {
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
