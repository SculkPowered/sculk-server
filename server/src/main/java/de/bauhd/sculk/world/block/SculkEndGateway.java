package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkEndGateway extends SculkBlockState.Entity<EndGateway> implements EndGateway {

  SculkEndGateway(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 21);
  }

  public SculkEndGateway(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull EndGateway nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkEndGateway(this.block, this.id, this.properties, this.entityId, nbt);
  }
}