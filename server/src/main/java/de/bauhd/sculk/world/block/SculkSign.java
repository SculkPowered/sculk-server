package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkSign extends SculkBlockState.Entity<Sign> implements Sign {

    SculkSign(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 7);
    }

    public SculkSign(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull Sign nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkSign(this.block, this.id, this.properties, this.entityId, nbt);
    }
}