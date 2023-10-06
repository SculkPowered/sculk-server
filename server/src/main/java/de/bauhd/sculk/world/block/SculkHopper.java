package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkHopper extends SculkBlockState.Entity<Hopper> implements Hopper {

    SculkHopper(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 17);
    }

    public SculkHopper(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull Hopper nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkHopper(this.block, this.id, this.properties, this.entityId, nbt);
    }
}