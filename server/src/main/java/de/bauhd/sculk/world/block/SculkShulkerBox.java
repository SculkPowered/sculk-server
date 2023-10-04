package de.bauhd.sculk.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkShulkerBox extends SculkBlockState.Entity<ShulkerBox> implements ShulkerBox {

    SculkShulkerBox(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties, 23);
    }

    public SculkShulkerBox(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
        super(block, id, properties, entityId, nbt);
    }

    @Override
    public @NotNull ShulkerBox nbt(@NotNull CompoundBinaryTag nbt) {
         return new SculkShulkerBox(this.block, this.id, this.properties, this.entityId, nbt);
    }
}