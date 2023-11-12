package io.github.sculkpowered.server.world.block;

import io.github.sculkpowered.server.registry.Registry;
import org.jetbrains.annotations.ApiStatus;

/**
 * This class is only for internal use!
 */
@ApiStatus.Internal
final class Blocks {

  private static Registry<BlockState> REGISTRY;

  private Blocks() {
    throw new AssertionError();
  }

  static Registry<BlockState> registry() {
    return REGISTRY;
  }

  static void set(final Registry<BlockState> registry) {
    REGISTRY = registry;
  }
}
