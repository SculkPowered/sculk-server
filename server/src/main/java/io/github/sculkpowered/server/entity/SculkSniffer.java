package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkSniffer extends AbstractAnimal implements Sniffer {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.SNIFFER;
  }
}
