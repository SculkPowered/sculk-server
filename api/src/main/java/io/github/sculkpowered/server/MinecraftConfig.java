package io.github.sculkpowered.server;

import org.jetbrains.annotations.NotNull;

public interface MinecraftConfig {

  @NotNull String host();

  int port();

  @NotNull Mode mode();

  enum Mode {
    ONLINE,
    OFFLINE,
    BUNGEECORD
  }

  int compressionThreshold();

  int compressionLevel();
}
