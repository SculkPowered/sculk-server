package de.bauhd.minecraft.server.dimension;

import de.bauhd.minecraft.server.world.dimension.Dimension;
import de.bauhd.minecraft.server.world.dimension.DimensionHandler;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public final class MinecraftDimensionHandler implements DimensionHandler {

    private CompoundBinaryTag nbt;

    public MinecraftDimensionHandler() {
        this.nbt = CompoundBinaryTag.builder()
                .putString("type", "minecraft:dimension_type")
                .put("value", ListBinaryTag.from(List.of(Dimension.OVERWORLD.nbt()))) // OVERWORLD as default dimension
                .build();
    }

    @Override
    public void registerDimension(@NotNull Dimension dimension) {
        this.nbt = this.nbt.put("value", this.nbt.getList("value").add(dimension.nbt()));
    }

    @Override
    public @NotNull Collection<Dimension> getDimensions() {
        return null;
    }

    public CompoundBinaryTag nbt() {
        return this.nbt;
    }
}
