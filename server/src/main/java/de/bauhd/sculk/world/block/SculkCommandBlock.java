package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkCommandBlock extends SculkBlockState.Entity<CommandBlock> implements CommandBlock {

  SculkCommandBlock(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 22);
  }

  public SculkCommandBlock(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull CommandBlock nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkCommandBlock(this.block, this.id, this.properties, this.entityId, nbt);
  }
}