package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkEnderChest extends SculkBlockState.Entity<EnderChest> implements EnderChest {

  SculkEnderChest(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 3);
  }

  public SculkEnderChest(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull EnderChest nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkEnderChest(this.block, this.id, this.properties, this.entityId, nbt);
  }
}