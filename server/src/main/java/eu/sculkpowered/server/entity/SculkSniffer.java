package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkSniffer extends AbstractAnimal implements Sniffer {

  public SculkSniffer(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.SNIFFER;
  }
}
