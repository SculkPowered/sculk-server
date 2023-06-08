package de.bauhd.minecraft.server.world.dimension;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class MineDimensionHandler implements DimensionHandler {

    private final Map<String, Dimension> dimensions;

    public MineDimensionHandler() {
        this.dimensions = new HashMap<>();
        this.registerDimension(Dimension.OVERWORLD);
    }

    @Override
    public void registerDimension(@NotNull Dimension dimension) {
        this.dimensions.put(dimension.name(), dimension);
    }

    @Override
    public @NotNull Collection<Dimension> getDimensions() {
        return this.dimensions.values();
    }

    @Override
    public @NotNull Dimension getDimension(String name) {
        final var biome = this.dimensions.get(name);
        return biome != null ? biome : Dimension.OVERWORLD;
    }

    public CompoundBinaryTag nbt() {
        return CompoundBinaryTag.builder()
                .putString("type", "minecraft:dimension_type")
                .put("value", ListBinaryTag.from(this.dimensions.values().stream().map(Dimension::nbt).toList()))
                .build();
    }
}
