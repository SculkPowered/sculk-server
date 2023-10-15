package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkDaylightDetector extends SculkBlockState.Entity<DaylightDetector> implements DaylightDetector {

  SculkDaylightDetector(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 16);
  }

  public SculkDaylightDetector(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull DaylightDetector nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkDaylightDetector(this.block, this.id, this.properties, this.entityId, nbt);
  }
}