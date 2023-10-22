package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class SculkLecternContainer extends SculkContainer implements LecternContainer {

  private int page;

  public SculkLecternContainer(final Component title) {
    super(title);
  }

  @Override
  public void setPage(int page) {
    this.sendProperty(0, page);
    this.page = page;
  }

  @Override
  public int getPage() {
    return this.page;
  }

  @Override
  public @NotNull Type getType() {
    return Type.LECTERN;
  }

  @Override
  public void sendProperties(SculkPlayer player) {
    player.send(this.property(0, this.page));
  }
}
