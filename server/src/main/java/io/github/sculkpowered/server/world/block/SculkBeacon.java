package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkBeacon extends SculkBlockState.Entity<Beacon> implements Beacon {

  SculkBeacon(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 14);
  }

  public SculkBeacon(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Beacon nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkBeacon(this.block, this.id, this.properties, this.entityId, nbt);
  }
}