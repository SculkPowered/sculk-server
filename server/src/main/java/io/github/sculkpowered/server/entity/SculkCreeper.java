package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkCreeper extends AbstractMob implements Creeper {

  public SculkCreeper(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.CREEPER;
  }

  @Override
  public boolean isIdling() {
    return this.metadata.getVarInt(16, -1) == -1;
  }

  @Override
  public void idling() {
    this.metadata.setVarInt(16, -1);
  }

  @Override
  public boolean isFused() {
    return this.metadata.getVarInt(16, -1) == 1;
  }

  @Override
  public void fusing() {
    this.metadata.setVarInt(16, 1);
  }

  @Override
  public boolean isCharged() {
    return this.metadata.getBoolean(17, false);
  }

  @Override
  public void charged(boolean charged) {
    this.metadata.setBoolean(17, charged);
  }

  @Override
  public boolean isIgnited() {
    return this.metadata.getBoolean(18, false);
  }

  @Override
  public void ignited(boolean ignited) {
    this.metadata.setBoolean(18, ignited);
  }
}
