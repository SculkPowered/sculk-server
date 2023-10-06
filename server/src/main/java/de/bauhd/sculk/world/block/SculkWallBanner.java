package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkWallBanner extends SculkBlockState.Entity<WallBanner> implements WallBanner {

    SculkWallBanner(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 19);
    }

    public SculkWallBanner(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull WallBanner nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkWallBanner(this.block, this.id, this.properties, this.entityId, nbt);
    }
}