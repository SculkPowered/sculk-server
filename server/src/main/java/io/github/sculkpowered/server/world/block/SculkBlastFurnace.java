package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkBlastFurnace extends SculkBlockState.Entity<BlastFurnace> implements BlastFurnace {

  SculkBlastFurnace(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 28);
  }

  public SculkBlastFurnace(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull BlastFurnace nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkBlastFurnace(this.block, this.id, this.properties, this.entityId, nbt);
  }
}