package de.bauhd.sculk.event.player;

import de.bauhd.sculk.container.Container;
import de.bauhd.sculk.entity.player.Player;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when the client clicks at a container button.
 */
@SuppressWarnings("ClassCanBeRecord")
public final class PlayerClickContainerButtonEvent {

  private final @NotNull Player player;
  private final int button;

  public PlayerClickContainerButtonEvent(@NotNull Player player, int button) {
    this.player = player;
    this.button = button;
  }

  public @NotNull Player getPlayer() {
    return this.player;
  }

  public @NotNull Container getContainer() {
    return Objects.requireNonNull(this.player.getOpenedContainer());
  }

  public int getButton() {
    return this.button;
  }
}
