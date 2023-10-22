package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkIllusioner extends AbstractMob implements Illusioner {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.ILLUSIONER;
  }
}
