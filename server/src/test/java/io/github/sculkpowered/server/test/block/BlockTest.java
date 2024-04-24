package io.github.sculkpowered.server.test.block;

import io.github.sculkpowered.server.world.block.BlockRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class BlockTest {

  @Test
  public void test() {
    Assertions.assertDoesNotThrow(() -> BlockRegistry.get());
  }
}
