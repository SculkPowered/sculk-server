package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkWallHead extends SculkBlockState.Entity<WallHead> implements WallHead {

    SculkWallHead(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 15);
    }

    public SculkWallHead(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull WallHead nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkWallHead(this.block, this.id, this.properties, this.entityId, nbt);
    }
}