package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkSculkCatalyst extends SculkBlockState.Entity<SculkCatalyst> implements SculkCatalyst {

  SculkSculkCatalyst(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 36);
  }

  public SculkSculkCatalyst(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull SculkCatalyst nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkSculkCatalyst(this.block, this.id, this.properties, this.entityId, nbt);
  }
}