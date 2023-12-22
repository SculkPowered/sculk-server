package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.potion.PotionEffectType;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class SculkBeaconContainer extends SculkContainer implements BeaconContainer {

  private int powerLevel;
  private PotionEffectType firstPotionEffectType;
  private PotionEffectType secondPotionEffectType;

  public SculkBeaconContainer(final Component title) {
    super(Type.BEACON, title);
  }

  @Override
  public void powerLevel(@Range(from = 0, to = 4) int powerLevel) {
    this.sendProperty(0, powerLevel);
    this.powerLevel = powerLevel;
  }

  @Override
  public int powerLevel() {
    return this.powerLevel;
  }

  @Override
  public void firstPotionEffect(@NotNull PotionEffectType potionEffectType) {
    this.sendProperty(1, potionEffectType.ordinal());
    this.firstPotionEffectType = potionEffectType;
  }

  @Override
  public @NotNull PotionEffectType firstPotionEffect() {
    return this.firstPotionEffectType;
  }

  @Override
  public void secondPotionEffect(@NotNull PotionEffectType potionEffectType) {
    this.sendProperty(2, potionEffectType.ordinal());
    this.secondPotionEffectType = potionEffectType;
  }

  @Override
  public @NotNull PotionEffectType secondPotionEffect() {
    return this.secondPotionEffectType;
  }

  @Override
  public void sendProperties(SculkPlayer player) {
    player.send(this.property(0, this.powerLevel));
    player.send(this.property(1, this.firstPotionEffectType.ordinal()));
    player.send(this.property(2, this.secondPotionEffectType.ordinal()));
  }
}
