package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkCalibratedSculkSensor extends SculkBlockState.Entity<CalibratedSculkSensor> implements CalibratedSculkSensor {

  SculkCalibratedSculkSensor(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 35);
  }

  public SculkCalibratedSculkSensor(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull CalibratedSculkSensor nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkCalibratedSculkSensor(this.block, this.id, this.properties, this.entityId, nbt);
  }
}