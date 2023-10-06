package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkFurnace extends SculkBlockState.Entity<Furnace> implements Furnace {

    SculkFurnace(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 0);
    }

    public SculkFurnace(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull Furnace nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkFurnace(this.block, this.id, this.properties, this.entityId, nbt);
    }
}