package eu.sculkpowered.server.container;

import eu.sculkpowered.server.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Range;

public final class SculkBrewingStandContainer extends SculkContainer implements
    BrewingStandContainer {

  private int brewTime;
  private int fuelTime;

  public SculkBrewingStandContainer(final Component title) {
    super(Type.BREWING_STAND, title);
  }

  @Override
  public void brewTime(int brewTime) {
    this.sendProperty(0, brewTime);
    this.brewTime = brewTime;
  }

  @Override
  public int brewTime() {
    return this.brewTime;
  }

  @Override
  public void fuelTime(@Range(from = 0, to = 20) int fuelTime) {
    this.sendProperty(1, fuelTime);
    this.fuelTime = fuelTime;
  }

  @Override
  public int fuelTime() {
    return this.fuelTime;
  }

  @Override
  public void sendProperties(SculkPlayer player) {
    player.send(this.property(0, this.brewTime));
    player.send(this.property(1, this.fuelTime));
  }
}
