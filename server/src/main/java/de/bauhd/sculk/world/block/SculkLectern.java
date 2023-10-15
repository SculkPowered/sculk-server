package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkLectern extends SculkBlockState.Entity<Lectern> implements Lectern {

  SculkLectern(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 29);
  }

  public SculkLectern(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Lectern nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkLectern(this.block, this.id, this.properties, this.entityId, nbt);
  }
}