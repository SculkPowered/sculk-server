package de.bauhd.sculk.entity;

import de.bauhd.sculk.world.block.Block;
import de.bauhd.sculk.world.block.BlockState;
import org.jetbrains.annotations.NotNull;

public final class SculkBlockDisplay extends AbstractDisplay implements BlockDisplay {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BLOCK_DISPLAY;
    }

    @Override
    public @NotNull BlockState getBlock() {
        return Block.get(this.metadata.getVarInt(22, 0));
    }

    @Override
    public void setBlock(@NotNull BlockState block) {
        this.metadata.setBlockId(22, block.getId());
    }
}
