package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkBrewingStand extends SculkBlockState.Entity<BrewingStand> implements BrewingStand {

    SculkBrewingStand(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 11);
    }

    public SculkBrewingStand(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull BrewingStand nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkBrewingStand(this.block, this.id, this.properties, this.entityId, nbt);
    }
}