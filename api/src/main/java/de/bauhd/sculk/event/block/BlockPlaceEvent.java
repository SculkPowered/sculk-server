package de.bauhd.sculk.event.block;

import de.bauhd.sculk.entity.player.Player;
import de.bauhd.sculk.event.ResultedEvent;
import de.bauhd.sculk.world.Position;
import de.bauhd.sculk.world.block.BlockState;
import org.jetbrains.annotations.NotNull;

public final class BlockPlaceEvent extends ResultedEvent<ResultedEvent.GenericResult> {

  private final @NotNull Player player;
  private final @NotNull Position position;
  private final @NotNull BlockState block;

  public BlockPlaceEvent(@NotNull Player player, @NotNull Position position,
      @NotNull BlockState block) {
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
