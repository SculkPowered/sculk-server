package io.github.sculkpowered.server.event.block;

import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.event.ResultedEvent;
import io.github.sculkpowered.server.world.Position;
import io.github.sculkpowered.server.world.block.BlockState;
import org.jetbrains.annotations.NotNull;

public final class BlockPlaceEvent extends ResultedEvent<ResultedEvent.GenericResult> {

  private final Player player;
  private final Position position;
  private final BlockState block;

  public BlockPlaceEvent(@NotNull Player player, @NotNull Position position,
      @NotNull BlockState block) {
    this.player = player;
    this.position = position;
    this.block = block;
    this.result = GenericResult.allow();
  }

  public @NotNull Player player() {
    return this.player;
  }

  public @NotNull Position position() {
    return this.position;
  }

  public @NotNull BlockState block() {
    return this.block;
  }
}
