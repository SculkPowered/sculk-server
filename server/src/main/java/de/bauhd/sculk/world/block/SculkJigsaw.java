package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkJigsaw extends SculkBlockState.Entity<Jigsaw> implements Jigsaw {

    SculkJigsaw(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 31);
    }

    public SculkJigsaw(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull Jigsaw nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkJigsaw(this.block, this.id, this.properties, this.entityId, nbt);
    }
}