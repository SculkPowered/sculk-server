package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkGoat extends AbstractAnimal implements Goat {

  public SculkGoat(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.GOAT;
  }

  @Override
  public boolean isScreaming() {
    return this.metadata.getBoolean(17, false);
  }

  @Override
  public void isScreaming(boolean screamingGoat) {
    this.metadata.setBoolean(17, screamingGoat);
  }

  @Override
  public boolean hasLeftHorn() {
    return this.metadata.getBoolean(18, true);
  }

  @Override
  public void hasLeftHorn(boolean leftHorn) {
    this.metadata.setBoolean(18, leftHorn);
  }

  @Override
  public boolean hasRightHorn() {
    return this.metadata.getBoolean(19, true);
  }

  @Override
  public void rightHorn(boolean rightHorn) {
    this.metadata.setBoolean(19, rightHorn);
  }
}
