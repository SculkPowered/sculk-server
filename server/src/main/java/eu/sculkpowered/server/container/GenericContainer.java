package eu.sculkpowered.server.container;

import eu.sculkpowered.server.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;

public final class GenericContainer extends SculkContainer {

  public GenericContainer(final Type type, final Component title) {
    super(type, title);
  }

  @Override
  public void sendProperties(SculkPlayer player) {
  }
}
