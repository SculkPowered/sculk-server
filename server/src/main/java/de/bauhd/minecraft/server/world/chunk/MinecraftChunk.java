package de.bauhd.minecraft.server.world.chunk;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.play.ChunkDataAndUpdateLight;
import de.bauhd.minecraft.server.world.MinecraftWorld;
import de.bauhd.minecraft.server.world.biome.Biome;
import de.bauhd.minecraft.server.world.dimension.Dimension;
import de.bauhd.minecraft.server.world.section.Section;
import io.netty5.buffer.DefaultBufferAllocators;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public final class MinecraftChunk implements Chunk {

    private static final BitSet EMPTY_BIT_SET = new BitSet();
    private static final BitSet EMPTY_LIGHT = BitSet.valueOf(
            new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24});

    private final AdvancedMinecraftServer server;
    private final MinecraftWorld world;
    private final Dimension dimension;
    private final int x;
    private final int z;
    private final Section[] sections;

    private ChunkDataAndUpdateLight packet;

    public MinecraftChunk(final AdvancedMinecraftServer server, final MinecraftWorld world, final int chunkX, final int chunkZ) {
        this.server = server;
        this.world = world;
        this.dimension = this.world.getDimension();
        this.x = chunkX;
        this.z = chunkZ;

        final var capacity = this.dimension.maximumSections() - this.dimension.minimumSections();
        this.sections = new Section[capacity];
        for (int i = 0; i < capacity; i++) {
            this.sections[i] = new Section();
        }
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
    public void setBlock(int x, int y, int z, @NotNull Key key) {
        this.setBlock(x, y, z, this.server.getBlockRegistry().getId(key));
    }

    public void setBlock(int x, int y, int z, int id) {
        this.section(y).blocks().set(this.relativeCoordinate(x), this.relativeCoordinate(y), this.relativeCoordinate(z), id);
        this.packet = null;
    }

    @Override
    public void setBiome(int x, int y, int z, @NotNull Biome biome) {
        this.section(y).biomes().set(this.relativeCoordinate(x) / 4, this.relativeCoordinate(y) / 4, this.relativeCoordinate(z) / 4,
                biome.nbt().getInt("id"));
        this.packet = null;
    }

    public void send(MinecraftPlayer player) {
        if (this.packet == null) {
            this.packet = new ChunkDataAndUpdateLight(
                    this.x,
                    this.z,
                    Dimension.OVERWORLD.heightmaps(),
                    this.sectionsToData(),
                    true,
                    EMPTY_BIT_SET,
                    EMPTY_BIT_SET,
                    EMPTY_LIGHT,
                    EMPTY_LIGHT
            );
        }
        player.send(this.packet);
    }

    public Section section(final int y) {
        return this.sections[this.world.chunkCoordinate(y) - this.dimension.minimumSections()];
    }

    private byte[] sectionsToData() {
        final var buf = new Buffer(DefaultBufferAllocators.offHeapAllocator().allocate(this.sections.length * 8)); // minimum amount
        for (final var section : this.sections) {
            buf.writeShort((short) section.blocks().size());
            section.blocks().write(buf);
            section.biomes().write(buf);
        }
        final var data = buf.readAll();
        buf.close();
        return data;
    }

    private int relativeCoordinate(final int coordinate) {
        return coordinate & 0xF;
    }

    @Override
    public String toString() {
        return "MinecraftChunk{" +
                "dimension=" + this.dimension.getClass().getSimpleName() +
                ", x=" + this.x +
                ", z=" + this.z +
                '}';
    }
}
