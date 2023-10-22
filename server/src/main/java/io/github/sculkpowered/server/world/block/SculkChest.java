package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkChest extends SculkBlockState.Entity<Chest> implements Chest {

  SculkChest(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 1);
  }

  public SculkChest(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Chest nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkChest(this.block, this.id, this.properties, this.entityId, nbt);
  }
}