package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.world.block.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a block display entity.
 */
public interface BlockDisplay extends Display {

  /**
   * @since 1.0.0
   */
  @NotNull BlockState getBlock();

  /**
   * @since 1.0.0
   */
  void setBlock(@NotNull BlockState block);
}