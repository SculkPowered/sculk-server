package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public final class SculkWitch extends AbstractRaider implements Witch {

  @Override
  public @NotNull EntityType type() {
    return EntityType.WITCH;
  }

  @Override
  public boolean drinkingPotion() {
    return this.metadata.getBoolean(17, false);
  }

  @Override
  public void drinkingPotion(boolean drinkingPotion) {
    this.metadata.setBoolean(17, drinkingPotion);
  }
}
