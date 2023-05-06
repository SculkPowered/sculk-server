package de.bauhd.minecraft.server.world.biome;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public final class MinecraftBiomeHandler implements BiomeHandler {

    private CompoundBinaryTag nbt;

    public MinecraftBiomeHandler() {
        this.nbt = CompoundBinaryTag.builder()
                .putString("type", "minecraft:worldgen/biome")
                .put("value", ListBinaryTag.from(List.of(Biome.PLAINS.nbt()))) // PLAINS as default dimension
                .build();
    }

    @Override
    public void registerBiome(@NotNull Biome biome) {
        this.nbt = this.nbt.put("value", this.nbt.getList("value").add(biome.nbt()));
    }

    @Override
    public @NotNull Collection<Biome> getBiomes() {
        return null;
    }

    public CompoundBinaryTag nbt() {
        return this.nbt;
    }
}
