package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkLlamaSpit extends AbstractEntity implements LlamaSpit {

  public SculkLlamaSpit(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.LLAMA_SPIT;
  }
}
