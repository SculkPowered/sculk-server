package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkPig extends AbstractAnimal implements Pig {

  public SculkPig(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.PIG;
  }

  @Override
  public boolean hasSaddle() {
    return this.metadata.getBoolean(17, false);
  }

  @Override
  public void saddle(boolean saddle) {
    this.metadata.setBoolean(17, saddle);
  }
}
