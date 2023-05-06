package de.bauhd.minecraft.server.world;

import de.bauhd.minecraft.server.world.block.Block;
import de.bauhd.minecraft.server.world.chunk.ChunkGenerator;
import de.bauhd.minecraft.server.world.dimension.Dimension;
import org.jetbrains.annotations.NotNull;

public interface World {

    @NotNull String getName();

    @NotNull Dimension getDimension();

    @NotNull ChunkGenerator getGenerator();

    @NotNull Block getBlock(final int x, final int y, final int z);

}
