package eu.sculkpowered.server.event.block;

import eu.sculkpowered.server.entity.player.Player;
import eu.sculkpowered.server.event.ResultedEvent;
import eu.sculkpowered.server.world.Position;
import eu.sculkpowered.server.world.block.BlockState;
import org.jetbrains.annotations.NotNull;

public final class BlockBreakEvent extends ResultedEvent<ResultedEvent.GenericResult> {

  private final Player player;
  private final Position position;
  private final BlockState block;

  public BlockBreakEvent(
      @NotNull Player player,
      @NotNull Position position,
      @NotNull BlockState block
  ) {
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
