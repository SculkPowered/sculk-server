package eu.sculkpowered.server.container;

import eu.sculkpowered.server.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;

public final class SculkAnvilContainer extends SculkContainer implements AnvilContainer {

  private int repairCost;

  public SculkAnvilContainer(final Component title) {
    super(Type.ANVIL, title);
  }

  @Override
  public void repairCost(int repairCost) {
    this.sendProperty(0, repairCost);
    this.repairCost = repairCost;
  }

  @Override
  public int repairCost() {
    return this.repairCost;
  }

  @Override
  public void sendProperties(SculkPlayer player) {
    player.send(this.property(0, this.repairCost));
  }
}
