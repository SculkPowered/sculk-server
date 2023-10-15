package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkChiseledBookshelf extends SculkBlockState.Entity<ChiseledBookshelf> implements ChiseledBookshelf {

  SculkChiseledBookshelf(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 38);
  }

  public SculkChiseledBookshelf(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull ChiseledBookshelf nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkChiseledBookshelf(this.block, this.id, this.properties, this.entityId, nbt);
  }
}