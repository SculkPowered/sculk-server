package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkGlowItemFrame extends SculkItemFrame implements GlowItemFrame {

  public SculkGlowItemFrame(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.GLOW_ITEM_FRAME;
  }
}
