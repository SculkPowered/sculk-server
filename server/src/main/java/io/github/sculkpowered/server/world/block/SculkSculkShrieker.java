package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkSculkShrieker extends SculkBlockState.Entity<SculkShrieker> implements SculkShrieker {

  SculkSculkShrieker(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 37);
  }

  public SculkSculkShrieker(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull SculkShrieker nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkSculkShrieker(this.block, this.id, this.properties, this.entityId, nbt);
  }
}