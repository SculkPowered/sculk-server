package de.bauhd.minecraft.server.api.world;

import de.bauhd.minecraft.server.api.world.block.Block;
import de.bauhd.minecraft.server.api.world.dimension.Dimension;
import org.jetbrains.annotations.NotNull;

public interface World {

    @NotNull Dimension getDimension();

    @NotNull Block getBlock(final int x, final int y, final int z);

}
