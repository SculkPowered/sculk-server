package de.bauhd.minecraft.server.world.chunk;

import de.bauhd.minecraft.server.world.biome.Biome;
import de.bauhd.minecraft.server.world.block.Block;
import org.jetbrains.annotations.NotNull;

public interface Chunk {

    int CHUNK_SIZE_X = 16;
    int CHUNK_SIZE_Z = 16;
    int CHUNK_SECTION_SIZE = 16;

    int getX();

    int getZ();

    void setBlock(int x, int y, int z, @NotNull Block block);

    void setBiome(int x, int y, int z, @NotNull Biome biome);

}
