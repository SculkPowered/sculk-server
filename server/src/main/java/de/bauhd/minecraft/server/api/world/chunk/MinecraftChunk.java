package de.bauhd.minecraft.server.api.world.chunk;

import de.bauhd.minecraft.server.api.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.api.world.World;
import de.bauhd.minecraft.server.api.world.block.Block;
import de.bauhd.minecraft.server.api.world.dimension.Dimension;
import de.bauhd.minecraft.server.api.world.section.Section;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.play.ChunkDataAndUpdateLight;
import io.netty5.buffer.DefaultBufferAllocators;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public final class MinecraftChunk implements Chunk {

    private static final BitSet EMPTY_BIT_SET = new BitSet();
    private static final BitSet EMPTY_LIGHT = BitSet.valueOf(
            new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24});

    private final Dimension dimension;
    private final int x;
    private final int z;
    private final List<Section> sections;
    private final Int2ObjectMap<Block> blocks = new Int2ObjectOpenHashMap<>();

    public MinecraftChunk(final World world, final int chunkX, final int chunkZ) {
        this.dimension = world.getDimension();
        this.x = chunkX;
        this.z = chunkZ;

        final var capacity = this.dimension.maximumSections() - this.dimension.minimumSections();
        this.sections = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            this.sections.add(new Section());
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
    public void setBlock(int x, int y, int z, int stateId) {
        final var section = this.sections.get(this.chunkCoordinate(y) - this.dimension.minimumSections());
        section.blocks().set(this.relativeCoordinate(x), this.relativeCoordinate(y), this.relativeCoordinate(z), stateId);
    }

    public void send(MinecraftPlayer player) {
        player.send(new ChunkDataAndUpdateLight(
                this.x,
                this.z,
                Dimension.OVERWORLD.heightmaps(),
                this.sectionsToData(),
                true,
                EMPTY_BIT_SET,
                EMPTY_BIT_SET,
                EMPTY_LIGHT,
                EMPTY_LIGHT)
        );
    }

    private byte[] sectionsToData() {
        final var buf = new Buffer(DefaultBufferAllocators.offHeapAllocator().allocate(0));

        for (final var section : this.sections) {
            buf.writeShort((short) section.blocks().size());
            section.blocks().write(buf);
            section.biomes().write(buf);
        }
        final var data = buf.readAll();
        buf.close();
        return data;
    }

    private int chunkCoordinate(final int coordinate) {
        return coordinate >> 4;
    }

    private int relativeCoordinate(final int coordinate) {
        return coordinate & 0xF;
    }
}
