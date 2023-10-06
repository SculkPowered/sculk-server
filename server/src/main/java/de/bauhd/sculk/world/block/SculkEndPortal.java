package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkEndPortal extends SculkBlockState.Entity<EndPortal> implements EndPortal {

    SculkEndPortal(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 13);
    }

    public SculkEndPortal(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull EndPortal nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkEndPortal(this.block, this.id, this.properties, this.entityId, nbt);
    }
}