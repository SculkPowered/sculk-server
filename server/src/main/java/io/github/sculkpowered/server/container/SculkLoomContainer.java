package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;

public final class SculkLoomContainer extends SculkContainer implements LoomContainer {

  private int selectedPattern;

  public SculkLoomContainer(Component title) {
    super(Type.LOOM, title);
  }

  @Override
  public void selectedPattern(int pattern) {
    this.sendProperty(0, pattern);
    this.selectedPattern = pattern;
  }

  @Override
  public int selectedPattern() {
    return this.selectedPattern;
  }

  @Override
  public void sendProperties(SculkPlayer player) {
    player.send(this.property(0, this.selectedPattern));
  }
}
