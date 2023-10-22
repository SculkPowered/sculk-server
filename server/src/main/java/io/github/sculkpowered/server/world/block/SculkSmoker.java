package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkSmoker extends SculkBlockState.Entity<Smoker> implements Smoker {

  SculkSmoker(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 27);
  }

  public SculkSmoker(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Smoker nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkSmoker(this.block, this.id, this.properties, this.entityId, nbt);
  }
}