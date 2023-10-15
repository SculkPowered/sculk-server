package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkBarrel extends SculkBlockState.Entity<Barrel> implements Barrel {

  SculkBarrel(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 26);
  }

  public SculkBarrel(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Barrel nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkBarrel(this.block, this.id, this.properties, this.entityId, nbt);
  }
}