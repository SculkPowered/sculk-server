package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkStructureBlock extends SculkBlockState.Entity<StructureBlock> implements StructureBlock {

  SculkStructureBlock(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 20);
  }

  public SculkStructureBlock(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull StructureBlock nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkStructureBlock(this.block, this.id, this.properties, this.entityId, nbt);
  }
}