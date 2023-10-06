package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkSculkSensor extends SculkBlockState.Entity<SculkSensor> implements SculkSensor {

    SculkSculkSensor(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 34);
    }

    public SculkSculkSensor(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull SculkSensor nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkSculkSensor(this.block, this.id, this.properties, this.entityId, nbt);
    }
}