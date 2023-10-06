package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkWallSign extends SculkBlockState.Entity<WallSign> implements WallSign {

    SculkWallSign(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 7);
    }

    public SculkWallSign(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull WallSign nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkWallSign(this.block, this.id, this.properties, this.entityId, nbt);
    }
}