package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkDispenser extends SculkBlockState.Entity<Dispenser> implements Dispenser {

  SculkDispenser(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 5);
  }

  public SculkDispenser(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Dispenser nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkDispenser(this.block, this.id, this.properties, this.entityId, nbt);
  }
}