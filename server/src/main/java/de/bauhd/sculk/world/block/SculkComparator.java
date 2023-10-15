package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkComparator extends SculkBlockState.Entity<Comparator> implements Comparator {

  SculkComparator(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 18);
  }

  public SculkComparator(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Comparator nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkComparator(this.block, this.id, this.properties, this.entityId, nbt);
  }
}