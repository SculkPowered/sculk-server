package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.world.block.Block;
import io.github.sculkpowered.server.world.block.BlockState;
import org.jetbrains.annotations.NotNull;

public final class SculkBlockDisplay extends AbstractDisplay implements BlockDisplay {

  public SculkBlockDisplay(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.BLOCK_DISPLAY;
  }

  @Override
  public @NotNull BlockState block() {
    return Block.get(this.metadata.getVarInt(22, 0));
  }

  @Override
  public void block(@NotNull BlockState block) {
    this.metadata.setBlockId(22, block.id());
  }
}
