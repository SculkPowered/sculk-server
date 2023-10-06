package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkTrappedChest extends SculkBlockState.Entity<TrappedChest> implements TrappedChest {

    SculkTrappedChest(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 2);
    }

    public SculkTrappedChest(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull TrappedChest nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkTrappedChest(this.block, this.id, this.properties, this.entityId, nbt);
    }
}