package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.potion.PotionEffect;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class SculkBeaconContainer extends SculkContainer implements BeaconContainer {

  private int powerLevel;
  private PotionEffect firstPotionEffect;
  private PotionEffect secondPotionEffect;

  public SculkBeaconContainer(final Component title) {
    super(title);
  }

  @Override
  public void setPowerLevel(@Range(from = 0, to = 4) int powerLevel) {
    this.sendProperty(0, powerLevel);
    this.powerLevel = powerLevel;
  }

  @Override
  public int getPowerLevel() {
    return this.powerLevel;
  }

  @Override
  public void setFirstPotionEffect(@NotNull PotionEffect potionEffect) {
    this.sendProperty(1, potionEffect.ordinal());
    this.firstPotionEffect = potionEffect;
  }

  @Override
  public @NotNull PotionEffect getFirstPotionEffect() {
    return this.firstPotionEffect;
  }

  @Override
  public void setSecondPotionEffect(@NotNull PotionEffect potionEffect) {
    this.sendProperty(2, potionEffect.ordinal());
    this.secondPotionEffect = potionEffect;
  }

  @Override
  public @NotNull PotionEffect getSecondPotionEffect() {
    return this.secondPotionEffect;
  }

  @Override
  public @NotNull Type getType() {
    return Type.BEACON;
  }

  @Override
  public void sendProperties(SculkPlayer player) {
    player.send(this.property(0, this.powerLevel));
    player.send(this.property(1, this.firstPotionEffect.ordinal()));
    player.send(this.property(2, this.secondPotionEffect.ordinal()));
  }
}