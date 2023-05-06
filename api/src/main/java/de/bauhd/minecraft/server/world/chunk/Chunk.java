package de.bauhd.minecraft.server.world.chunk;

import de.bauhd.minecraft.server.world.biome.Biome;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public interface Chunk {

    int getX();

    int getZ();

    void setBlock(int x, int y, int z, @NotNull Key key);

    void setBiome(int x, int y, int z, @NotNull Biome biome);

}
