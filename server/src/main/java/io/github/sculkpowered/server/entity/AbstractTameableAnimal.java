package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractTameableAnimal extends AbstractAnimal implements Tameable {

  public AbstractTameableAnimal(final SculkServer server) {
    super(server);
  }

  @Override
  public boolean sitting() {
    return this.metadata.inMask(17, 0x01);
  }

  @Override
  public void sitting(boolean sitting) {
    this.metadata.setMask(17, 0x01, sitting);
  }

  @Override
  public boolean tamed() {
    return this.metadata.inMask(17, 0x04);
  }

  @Override
  public @Nullable UUID owner() {
    return this.metadata.getOptUUID(18, null);
  }
}
