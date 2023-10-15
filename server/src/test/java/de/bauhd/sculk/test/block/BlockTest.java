package de.bauhd.sculk.test.block;

import de.bauhd.sculk.world.block.BlockParent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class BlockTest {

  @Test
  public void test() {
    Assertions.assertDoesNotThrow(BlockParent::addBlocks);
  }
}
