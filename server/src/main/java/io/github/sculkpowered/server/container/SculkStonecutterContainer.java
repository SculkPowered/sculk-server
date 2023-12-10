package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;

public final class SculkStonecutterContainer extends SculkContainer implements
    StonecutterContainer {

  private int selectedRecipe;

  public SculkStonecutterContainer(final Component title) {
    super(Type.STONECUTTER, title);
  }

  @Override
  public void selectedRecipe(int recipe) {
    this.sendProperty(0, this.selectedRecipe);
    this.selectedRecipe = recipe;
  }

  @Override
  public int selectedRecipe() {
    return this.selectedRecipe;
  }

  @Override
  public void sendProperties(SculkPlayer player) {
    player.send(this.property(0, this.selectedRecipe));
  }
}
