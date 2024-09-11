package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkGhast extends AbstractMob implements Ghast {

  public SculkGhast(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.GHAST;
  }

  @Override
  public boolean attacking() {
    return this.metadata.getBoolean(16, false);
  }

  @Override
  public void attacking(boolean attacking) {
    this.metadata.setBoolean(16, attacking);
  }
}
