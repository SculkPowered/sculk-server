package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkPiston extends SculkBlockState.Entity<Piston> implements Piston {

  SculkPiston(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 10);
  }

  public SculkPiston(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Piston nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkPiston(this.block, this.id, this.properties, this.entityId, nbt);
  }
}