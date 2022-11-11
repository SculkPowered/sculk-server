package de.bauhd.minecraft.server.api.dimension;

import de.bauhd.minecraft.server.api.world.dimension.Dimension;
import de.bauhd.minecraft.server.api.world.dimension.DimensionHandler;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public final class MinecraftDimensionHandler implements DimensionHandler {

    private final CompoundBinaryTag nbt = CompoundBinaryTag.empty();

    @Override
    public void registerDimension(@NotNull Dimension dimension) {

    }

    @Override
    public @NotNull Collection<Dimension> getDimensions() {
        return null;
    }
}
