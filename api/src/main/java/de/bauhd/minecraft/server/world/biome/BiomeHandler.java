package de.bauhd.minecraft.server.world.biome;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface BiomeHandler {

    void registerBiome(@NotNull Biome biome);

    @NotNull Collection<Biome> getBiomes();

}
