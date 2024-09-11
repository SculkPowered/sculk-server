package eu.sculkpowered.server.test.block;

import eu.sculkpowered.server.world.block.BlockRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class BlockTest {

  @Test
  public void test() {
    Assertions.assertDoesNotThrow(() -> BlockRegistry.get());
  }
}
