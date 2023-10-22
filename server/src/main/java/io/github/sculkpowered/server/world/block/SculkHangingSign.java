package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkHangingSign extends SculkBlockState.Entity<HangingSign> implements HangingSign {

  SculkHangingSign(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 8);
  }

  public SculkHangingSign(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull HangingSign nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkHangingSign(this.block, this.id, this.properties, this.entityId, nbt);
  }
}