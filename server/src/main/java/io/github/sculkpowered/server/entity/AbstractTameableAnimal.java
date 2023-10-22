package io.github.sculkpowered.server.entity;

import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractTameableAnimal extends AbstractAnimal implements Tameable {

  @Override
  public boolean isSitting() {
    return this.metadata.inMask(17, 0x01);
  }

  @Override
  public void setSitting(boolean sitting) {
    this.metadata.setMask(17, 0x01, sitting);
  }

  @Override
  public boolean isTamed() {
    return this.metadata.inMask(17, 0x04);
  }

  @Override
  public @Nullable UUID getOwner() {
    return this.metadata.getOptUUID(18, null);
  }
}
