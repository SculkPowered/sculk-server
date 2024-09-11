package eu.sculkpowered.server.world.chunk;

import eu.sculkpowered.server.world.biome.Biome;
import eu.sculkpowered.server.world.block.BlockState;
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
   *
   * @return the x coordinate of the chunk
   * @since 1.0.0
   */
  int x();

  /**
   * Gets the z chunk coordinate.
   *
   * @return the z coordinate of the chunk
   * @since 1.0.0
   */
  int z();

  /**
   * Sets the block at the specified coordinates.
   *
   * @param x     the x coordinate
   * @param y     the y coordinate
   * @param z     the z coordinate
   * @param block the block to set
   * @since 1.0.0
   */
  void block(int x, int y, int z, @NotNull BlockState block);

  /**
   * Gets a block from his coordinates.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   * @param z the z coordinate
   * @return the block at the specified coordinates.
   * @since 1.0.0
   */
  @NotNull BlockState block(int x, int y, int z);

  /**
   * Sets the biome at the specified coordinates.
   *
   * @param x     the x coordinate
   * @param y     the y coordinate
   * @param z     the z coordinate
   * @param biome the block to set
   * @since 1.0.0
   */
  void biome(int x, int y, int z, @NotNull Biome biome);
}
