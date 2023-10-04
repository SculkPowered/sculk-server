package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkBeehive extends SculkBlockState.Entity<Beehive> implements Beehive {

    SculkBeehive(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 33);
    }

    public SculkBeehive(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull Beehive nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkBeehive(this.block, this.id, this.properties, this.entityId, nbt);
    }
}