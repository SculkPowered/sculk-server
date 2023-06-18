package de.bauhd.minecraft.server.world.chunk;

import de.bauhd.minecraft.server.world.biome.Biome;
import de.bauhd.minecraft.server.world.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a chunk of the world.
 */
public interface Chunk {

    int CHUNK_SIZE_X = 16;
    int CHUNK_SIZE_Z = 16;
    int CHUNK_SECTION_SIZE = 16;

    /**
     * Gets the x chunk coordinate.
     * @return the x coordinate of the chunk
     */
    int getX();

    /**
     * Gets the z chunk coordinate.
     * @return the z coordinate of the chunk
     */
    int getZ();

    /**
     * Sets the block at the specified coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param block the block to set
     */
    void setBlock(int x, int y, int z, @NotNull Block block);

    /**
     * Gets a block from his coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @return the block at the specified coordinates.
     */
    @NotNull Block getBlock(int x, int y, int z);

    /**
     * Sets the biome at the specified coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param biome the block to set
     */
    void setBiome(int x, int y, int z, @NotNull Biome biome);
}
