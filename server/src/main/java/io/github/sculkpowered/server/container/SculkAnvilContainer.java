package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class SculkAnvilContainer extends SculkContainer implements AnvilContainer {

  private int repairCost;

  public SculkAnvilContainer(final Component title) {
    super(title);
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
  public @NotNull Type type() {
    return Type.ANVIL;
  }

  @Override
  public void sendProperties(SculkPlayer player) {
    player.send(this.property(0, this.repairCost));
  }
}
