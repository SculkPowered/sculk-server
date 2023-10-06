package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkBanner extends SculkBlockState.Entity<Banner> implements Banner {

    SculkBanner(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 19);
    }

    public SculkBanner(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull Banner nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkBanner(this.block, this.id, this.properties, this.entityId, nbt);
    }
}