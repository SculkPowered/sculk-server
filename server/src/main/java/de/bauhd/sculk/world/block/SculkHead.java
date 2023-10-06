package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkHead extends SculkBlockState.Entity<Head> implements Head {

    SculkHead(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 15);
    }

    public SculkHead(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull Head nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkHead(this.block, this.id, this.properties, this.entityId, nbt);
    }
}