package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkBat extends AbstractAnimal implements Bat {

  public SculkBat(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.BAT;
  }

  @Override
  public boolean hanging() {
    return this.metadata.inMask(16, 0x01);
  }

  @Override
  public void hanging(boolean hanging) {
    this.metadata.setMask(16, 0x01, hanging);
  }
}
