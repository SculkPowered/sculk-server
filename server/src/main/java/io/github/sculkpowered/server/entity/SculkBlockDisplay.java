package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.world.block.Block;
import io.github.sculkpowered.server.world.block.BlockState;
import org.jetbrains.annotations.NotNull;

public final class SculkBlockDisplay extends AbstractDisplay implements BlockDisplay {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.BLOCK_DISPLAY;
  }

  @Override
  public @NotNull BlockState getBlock() {
    return Block.get(this.metadata.getVarInt(22, 0));
  }

  @Override
  public void setBlock(@NotNull BlockState block) {
    this.metadata.setBlockId(22, block.getId());
  }
}
