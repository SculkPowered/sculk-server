package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkConduit extends SculkBlockState.Entity<Conduit> implements Conduit {

  SculkConduit(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 25);
  }

  public SculkConduit(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Conduit nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkConduit(this.block, this.id, this.properties, this.entityId, nbt);
  }
}