package de.bauhd.minecraft.server.world.biome;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface BiomeHandler {

    /**
     * registers a biome
     * @param biome the biome to register
     */
    void registerBiome(@NotNull Biome biome);

    /**
     * @return a collection with all registered biomes
     */
    @NotNull Collection<Biome> getBiomes();

    /**
     * gets a biome from its name
     * @param name the name of the biome
     * @return biome from name or plains if is doesn't exist
     */
    @NotNull Biome getBiome(String name);
}
