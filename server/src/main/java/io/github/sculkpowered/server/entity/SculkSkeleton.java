package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public class SculkSkeleton extends AbstractMob implements Skeleton {

  @Override
  public @NotNull EntityType getType() {
    return EntityType.SKELETON;
  }
}