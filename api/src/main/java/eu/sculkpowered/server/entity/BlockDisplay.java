package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.world.block.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a block display entity.
 */
public interface BlockDisplay extends Display {

  /**
   * @since 1.0.0
   */
  @NotNull BlockState block();

  /**
   * @since 1.0.0
   */
  void block(@NotNull BlockState block);
}