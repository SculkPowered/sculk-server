package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkDecoratedPot extends SculkBlockState.Entity<DecoratedPot> implements DecoratedPot {

  SculkDecoratedPot(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 40);
  }

  public SculkDecoratedPot(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull DecoratedPot nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkDecoratedPot(this.block, this.id, this.properties, this.entityId, nbt);
  }
}