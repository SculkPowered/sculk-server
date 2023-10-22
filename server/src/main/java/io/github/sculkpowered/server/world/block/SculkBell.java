package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkBell extends SculkBlockState.Entity<Bell> implements Bell {

  SculkBell(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 30);
  }

  public SculkBell(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Bell nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkBell(this.block, this.id, this.properties, this.entityId, nbt);
  }
}