package de.bauhd.sculk;

import org.jetbrains.annotations.NotNull;

public final class SculkConfiguration implements MinecraftConfig {

  private final String host;
  private final int port;
  private final Mode mode;
  private final int compressionThreshold;
  private final int compressionLevel;

  public SculkConfiguration() {
    this.host = "0.0.0.0";
    this.port = 25565;
    this.mode = Mode.ONLINE;
    this.compressionThreshold = 256;
    this.compressionLevel = -1;
  }

  public @NotNull String host() {
    return this.host;
  }

  public int port() {
    return this.port;
  }

  @Override
  public @NotNull Mode mode() {
    return this.mode;
  }

  @Override
  public int compressionThreshold() {
    return this.compressionThreshold;
  }

  @Override
  public int compressionLevel() {
    return this.compressionLevel;
  }
}
