package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkEnchantingTable extends SculkBlockState.Entity<EnchantingTable> implements EnchantingTable {

  SculkEnchantingTable(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 12);
  }

  public SculkEnchantingTable(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull EnchantingTable nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkEnchantingTable(this.block, this.id, this.properties, this.entityId, nbt);
  }
}