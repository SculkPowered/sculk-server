package eu.sculkpowered.server.container;

import eu.sculkpowered.server.entity.player.SculkPlayer;
import eu.sculkpowered.server.potion.PotionType;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class SculkBeaconContainer extends SculkContainer implements BeaconContainer {

  private int powerLevel;
  private PotionType firstPotionType;
  private PotionType secondPotionType;

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
  public void firstPotionEffect(@NotNull PotionType potionType) {
    this.sendProperty(1, potionType.ordinal());
    this.firstPotionType = potionType;
  }

  @Override
  public @NotNull PotionType firstPotionEffect() {
    return this.firstPotionType;
  }

  @Override
  public void secondPotionEffect(@NotNull PotionType potionType) {
    this.sendProperty(2, potionType.ordinal());
    this.secondPotionType = potionType;
  }

  @Override
  public @NotNull PotionType secondPotionEffect() {
    return this.secondPotionType;
  }

  @Override
  public void sendProperties(SculkPlayer player) {
    player.send(this.property(0, this.powerLevel));
    player.send(this.property(1, this.firstPotionType.ordinal()));
    player.send(this.property(2, this.secondPotionType.ordinal()));
  }
}
