package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkCampfire extends SculkBlockState.Entity<Campfire> implements Campfire {

  SculkCampfire(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 32);
  }

  public SculkCampfire(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Campfire nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkCampfire(this.block, this.id, this.properties, this.entityId, nbt);
  }
}