package eu.sculkpowered.server.container;

import eu.sculkpowered.server.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;

public final class SculkLecternContainer extends SculkContainer implements LecternContainer {

  private int page;

  public SculkLecternContainer(final Component title) {
    super(Type.LECTERN, title);
  }

  @Override
  public void page(int page) {
    this.sendProperty(0, page);
    this.page = page;
  }

  @Override
  public int page() {
    return this.page;
  }

  @Override
  public void sendProperties(SculkPlayer player) {
    player.send(this.property(0, this.page));
  }
}
