package io.github.sculkpowered.server.event.block;

import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.event.ResultedEvent;
import io.github.sculkpowered.server.world.Position;
import io.github.sculkpowered.server.world.block.BlockState;
import org.jetbrains.annotations.NotNull;

public final class BlockBreakEvent extends ResultedEvent<ResultedEvent.GenericResult> {

  private final @NotNull Player player;
  private final @NotNull Position position;
  private final @NotNull BlockState block;

  public BlockBreakEvent(
      @NotNull Player player,
      @NotNull Position position,
      @NotNull BlockState block
  ) {
    this.player = player;
    this.position = position;
    this.block = block;
    this.result = GenericResult.allowed();
  }

  public @NotNull Player getPlayer() {
    return this.player;
  }

  public @NotNull Position getPosition() {
    return this.position;
  }

  public @NotNull BlockState getBlock() {
    return this.block;
  }
}
