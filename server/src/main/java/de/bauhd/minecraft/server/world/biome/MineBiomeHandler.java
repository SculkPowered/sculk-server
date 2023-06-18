package de.bauhd.minecraft.server.world.biome;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class MineBiomeHandler implements BiomeHandler {

    private final Map<String, Biome> biomes;

    public MineBiomeHandler() {
        this.biomes = new HashMap<>();
        this.register(Biome.PLAINS);
    }

    @Override
    public void register(@NotNull Biome biome) {
        this.biomes.put(biome.name(), biome);
    }

    @Override
    public @NotNull Collection<Biome> getBiomes() {
        return this.biomes.values();
    }

    @Override
    public @NotNull Biome getBiome(@NotNull String name) {
        final var biome = this.biomes.get(name);
        return biome != null ? biome : Biome.PLAINS;
    }

    public CompoundBinaryTag nbt() {
        return CompoundBinaryTag.builder()
                .putString("type", "minecraft:worldgen/biome")
                .put("value", ListBinaryTag.from(this.biomes.values().stream().map(Biome::nbt).toList()))
                .build();
    }
}
