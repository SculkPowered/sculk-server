package de.bauhd.minecraft.server.world.biome;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Handles the registration of biomes.
 */
public interface BiomeHandler {

    /**
     * Registers the specified {@link Biome}.
     * @param biome the biome to register
     */
    void register(@NotNull Biome biome);

    /**
     * Gets all registered biomes.
     * @return a collection with all registered biomes
     */
    @NotNull Collection<Biome> getBiomes();

    /**
     * Gets a biome from its name.
     * @param name the name of the biome
     * @return biome from name or {@code Biome.PLAINS} if is does not exist
     */
    @NotNull Biome getBiome(@NotNull String name);
}
